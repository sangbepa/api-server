package com.sangbepa.api.project.repository;

import org.springframework.stereotype.Repository;
import com.sangbepa.api.project.domain.ProjectDTO;
import com.sangbepa.api.common.domain.Messenger;
import java.util.List;

/**
 * Project Repository 구현체
 * DTO를 직접 사용하여 터미널에 출력하는 계층
 */
@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    /**
     * 프로젝트 배출량 정보를 터미널에 출력하고 Messenger 반환
     * 
     * @param projectList 출력할 프로젝트 DTO 리스트
     * @return 출력 결과를 담은 Messenger
     */
    @Override
    public Messenger printProjectInfo(List<ProjectDTO> projectList) {
        System.out.println("\n[ProjectRepository] 터미널 출력 시작");
        System.out.println("==========================================");
        System.out.println("  받은 데이터: " + projectList.size() + "개의 DTO");
        System.out.println("==========================================\n");

        for (int i = 0; i < projectList.size(); i++) {
            ProjectDTO dto = projectList.get(i);

            // 터미널 출력 (DTO의 getter 직접 사용)
            System.out.println("┌─────────────────────────────────────────┐");
            System.out.println("│  🏭  배출량 정보 #" + (i + 1));
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  📍 사이트        : " + dto.getSite());
            System.out.println("│  📊 Scope 1       : " + formatEmission(dto.getScope1Tco2e()));
            System.out.println("│  📊 Scope 2       : " + formatEmission(dto.getScope2Tco2e()));
            System.out.println("│  📊 Total (1+2)   : " + formatEmission(dto.getTotalScope12Tco2e()));
            System.out.println("│  📊 Scope 3       : " + formatEmission(dto.getScope3Tco2e()));
            System.out.println("└─────────────────────────────────────────┘\n");
        }

        System.out.println("==========================================");
        System.out.println("[ProjectRepository] ✓ " + projectList.size() + "개 출력 완료");
        System.out.println("==========================================\n");

        // Messenger 생성 및 반환
        Messenger messenger = new Messenger();
        messenger.setCode(0);
        messenger.setMessage("Repository: " + projectList.size() + "개의 배출량 정보를 성공적으로 출력했습니다");
        return messenger;
    }

    /**
     * 배출량 포맷팅
     */
    private String formatEmission(Double emission) {
        if (emission == null) {
            return "데이터 없음";
        }
        return String.format("%.3f tCO2e", emission);
    }
}
