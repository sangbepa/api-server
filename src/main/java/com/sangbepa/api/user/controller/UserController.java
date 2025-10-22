package com.sangbepa.api.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.sangbepa.api.user.service.UserService;
import com.sangbepa.api.user.domain.UserDTO;
import com.sangbepa.api.common.domain.Messenger;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * User Controller - MVC 연습용
 * CSV → DTO → Service → Repository 흐름 연습
 * Messenger를 Model에 담아서 View 반환
 */
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 상위 5명 추출 API
     * GET /user/list
     */
    @GetMapping("/user/list")
    public String getTop5Passengers(Model model) {
        System.out.println("\n==========================================");
        System.out.println("[Controller] GET /user/list - MVC 흐름 연습");
        System.out.println("==========================================");

        Messenger messenger = new Messenger();

        try {
            // 1. CSV 파일 읽기
            System.out.println("[Controller] 1단계: CSV 파일 읽기");
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream("static/csv/train.csv");

            if (inputStream == null) {
                messenger.setCode(-1);
                messenger.setMessage("오류: CSV 파일을 찾을 수 없습니다");
                model.addAttribute("messenger", messenger);
                return "user/list"; // View 반환
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            int lineNumber = 0;
            List<UserDTO> top5 = new ArrayList<>();

            // 2. CSV 파싱하여 DTO에 담기 (상위 5명만)
            System.out.println("[Controller] 2단계: CSV → DTO 변환 (상위 5명)");

            while ((line = reader.readLine()) != null && top5.size() < 5) {
                lineNumber++;

                // 헤더 라인 건너뛰기
                if (lineNumber == 1) {
                    continue;
                }

                try {
                    // CSV 파싱
                    String[] fields = line.split(",");

                    if (fields.length >= 12) {
                        // UserDTO 생성 (기본 생성자 + Setter 사용)
                        UserDTO dto = new UserDTO();

                        // Setter를 이용한 데이터 설정
                        dto.setPassengerId(parseInteger(fields[0]));
                        dto.setSurvived(parseInteger(fields[1]));
                        dto.setPclass(parseInteger(fields[2]));
                        dto.setName(fields[3]);
                        dto.setSex(fields[4]);
                        dto.setAge(parseDouble(fields[5]));
                        dto.setSibSp(parseInteger(fields[6]));
                        dto.setParch(parseInteger(fields[7]));
                        dto.setTicket(fields[8]);
                        dto.setFare(parseDouble(fields[9]));
                        dto.setCabin(fields[10]);
                        dto.setEmbarked(fields[11]);

                        top5.add(dto);

                        System.out.println("  → DTO #" + top5.size() + " 생성: " + dto.getName());
                    }
                } catch (Exception e) {
                    // 파싱 오류 무시
                }
            }
            reader.close();

            // 3. Service로 전달하고 Repository의 Messenger 받기
            if (top5.size() > 0) {
                System.out.println("[Controller] 3단계: Service로 " + top5.size() + "개의 DTO 전달");
                messenger = userService.passToRepository(top5);

                System.out.println("[Controller] ✓ Repository의 Messenger 수신");

                // Model에 데이터 담기
                model.addAttribute("passengers", top5);
                model.addAttribute("count", top5.size());
            } else {
                messenger.setCode(1);
                messenger.setMessage("실패: 데이터를 추출하지 못했습니다");
                System.out.println("[Controller] ✗ 실패");
            }

        } catch (Exception e) {
            messenger.setCode(-1);
            messenger.setMessage("오류 발생: " + e.getMessage());
            System.out.println("[Controller] ❌ 오류: " + e.getMessage());
        }

        // Model에 Messenger 담기 (LoginController 패턴)
        model.addAttribute("messenger", messenger);

        System.out.println("==========================================\n");

        // View 이름 반환
        return "user/list";
    }

    /**
     * String을 Integer로 변환
     */
    private Integer parseInteger(String value) {
        try {
            return value == null || value.trim().isEmpty() ? null : Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * String을 Double로 변환
     */
    private Double parseDouble(String value) {
        try {
            return value == null || value.trim().isEmpty() ? null : Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
