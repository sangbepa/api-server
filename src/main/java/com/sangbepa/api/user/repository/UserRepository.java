package com.sangbepa.api.user.repository;

import org.springframework.stereotype.Repository;
import com.sangbepa.api.user.domain.UserDTO;
import com.sangbepa.api.user.domain.UserVO;
import com.sangbepa.api.common.domain.Messenger;
import java.util.List;

/**
 * User Repository - MVC 연습용
 * 실제 터미널 출력을 실행하는 계층
 */
@Repository
public class UserRepository {

    /**
     * 승객 정보를 터미널에 출력하고 Messenger 반환
     * 
     * @param passengers 출력할 승객 DTO 리스트
     * @return 출력 결과를 담은 Messenger
     */
    public Messenger printPassengerInfo(List<UserDTO> passengers) {
        System.out.println("\n[Repository] 터미널 출력 시작");
        System.out.println("==========================================");
        System.out.println("  받은 데이터: " + passengers.size() + "개의 DTO");
        System.out.println("==========================================\n");

        for (int i = 0; i < passengers.size(); i++) {
            UserDTO dto = passengers.get(i); // get 사용

            // DTO → VO 변환 (읽기 전용)
            UserVO vo = new UserVO(
                    dto.getPassengerId(),
                    dto.getSurvived(),
                    dto.getPclass(),
                    dto.getName(),
                    dto.getSex(),
                    dto.getAge(),
                    dto.getSibSp(),
                    dto.getParch(),
                    dto.getTicket(),
                    dto.getFare(),
                    dto.getCabin(),
                    dto.getEmbarked());

            // 터미널 출력 (get 사용)
            System.out.println("┌─────────────────────────────────────────┐");
            System.out.println("│  👤 승객 #" + (i + 1) + " 정보");
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  ID        : " + vo.getPassengerId());
            System.out.println("│  이름      : " + vo.getName());
            System.out.println("│  성별      : " + vo.getSex());
            System.out.println("│  나이      : " + (vo.getAge() != null ? vo.getAge() + "세" : "알 수 없음"));
            System.out.println("│  생존 여부 : " + (vo.getSurvived() == 1 ? "✓ 생존" : "✗ 사망"));
            System.out.println("│  등급      : " + vo.getPclass() + "등석");
            System.out.println("│  요금      : $" + (vo.getFare() != null ? vo.getFare() : "0"));
            System.out.println("│  티켓      : " + vo.getTicket());
            System.out.println(
                    "│  객실      : " + (vo.getCabin() != null && !vo.getCabin().isEmpty() ? vo.getCabin() : "미배정"));
            System.out.println("│  형제자매  : " + vo.getSibSp());
            System.out.println("│  부모자녀  : " + vo.getParch());
            System.out.println("│  탑승 항구 : " + vo.getEmbarked());
            System.out.println("└─────────────────────────────────────────┘\n");
        }

        System.out.println("==========================================");
        System.out.println("[Repository] ✓ " + passengers.size() + "명 출력 완료");
        System.out.println("==========================================\n");

        // Messenger 생성 및 반환
        Messenger messenger = new Messenger();
        messenger.setCode(0);
        messenger.setMessage("Repository: " + passengers.size() + "명의 승객 정보를 성공적으로 출력했습니다");
        return messenger;
    }
}
