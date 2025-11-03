package com.sangbepa.api.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.sangbepa.api.project.service.ProjectService;
import com.sangbepa.api.project.domain.ProjectDTO;
import com.sangbepa.api.common.domain.Messenger;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * Project Controller - 삼성 배출량 데이터 표시
 * CSV → DTO → Service → Repository 흐름
 */
@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * 프로젝트 목록 페이지 표시
     * GET /project/list
     */
    @GetMapping("/project/list")
    public String showList(Model model) {
        return "project/list";
    }

    /**
     * 프로젝트 데이터 로드 API
     * POST /project/all
     */
    @PostMapping("/project/all")
    public String loadProjectData(Model model) {
        System.out.println("\n==========================================");
        System.out.println("[ProjectController] POST /project/all - 삼성 배출량 데이터 로드");
        System.out.println("==========================================");

        Messenger messenger = new Messenger();

        try {
            // 1. CSV 파일 읽기
            System.out.println("[ProjectController] 1단계: CSV 파일 읽기");
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream("static/csv/emissions_samsung.csv");

            if (inputStream == null) {
                messenger.setCode(-1);
                messenger.setMessage("오류: CSV 파일을 찾을 수 없습니다");
                model.addAttribute("messenger", messenger);
                return "project/list";
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            int lineNumber = 0;
            List<ProjectDTO> projectList = new ArrayList<>();

            // 2. CSV 파싱하여 ProjectDTO에 담기
            System.out.println("[ProjectController] 2단계: CSV → ProjectDTO 변환");

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // 헤더 라인 건너뛰기
                if (lineNumber == 1) {
                    continue;
                }

                // 빈 라인 건너뛰기
                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    // CSV 파싱
                    String[] fields = line.split(",");

                    if (fields.length >= 1 && !fields[0].trim().isEmpty()) {
                        // ProjectDTO 생성
                        ProjectDTO dto = new ProjectDTO();

                        dto.setSite(fields[0].trim()); // 사이트명
                        dto.setScope1Tco2e(parseDouble(fields.length > 1 ? fields[1] : null)); // Scope 1
                        dto.setScope2Tco2e(parseDouble(fields.length > 2 ? fields[2] : null)); // Scope 2
                        dto.setTotalScope12Tco2e(parseDouble(fields.length > 3 ? fields[3] : null)); // Total Scope 1+2
                        dto.setScope3Tco2e(parseDouble(fields.length > 4 ? fields[4] : null)); // Scope 3

                        projectList.add(dto);

                        System.out.println("  → ProjectDTO #" + projectList.size() + " 생성: " + dto.getSite());
                    }
                } catch (Exception e) {
                    System.out.println("  ⚠ 라인 " + lineNumber + " 파싱 오류 (무시): " + e.getMessage());
                }
            }
            reader.close();

            // 3. Service로 전달하고 Repository의 Messenger 받기
            if (projectList.size() > 0) {
                System.out.println("[ProjectController] 3단계: Service로 " + projectList.size() + "개의 ProjectDTO 전달");
                messenger = projectService.passToRepository(projectList);

                System.out.println("[ProjectController] ✓ Repository의 Messenger 수신");

                // Model에 데이터 담기
                model.addAttribute("projectList", projectList);
                model.addAttribute("count", projectList.size());
            } else {
                messenger.setCode(1);
                messenger.setMessage("실패: 데이터를 추출하지 못했습니다");
                System.out.println("[ProjectController] ✗ 실패");
            }

        } catch (Exception e) {
            messenger.setCode(-1);
            messenger.setMessage("오류 발생: " + e.getMessage());
            System.out.println("[ProjectController] ❌ 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // Model에 Messenger 담기
        model.addAttribute("messenger", messenger);

        System.out.println("==========================================\n");

        // View 이름 반환
        return "project/list";
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
