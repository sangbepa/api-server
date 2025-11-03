package com.sangbepa.api.project.service;

import com.sangbepa.api.project.domain.ProjectDTO;
import com.sangbepa.api.common.domain.Messenger;
import com.sangbepa.api.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Project Service 구현체 - 파이프라인
 * Repository로 요청 전달 (단순 파이프라인)
 */
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    /**
     * BULK - CSV 데이터 일괄 처리
     * 파이프라인: Controller → Service → Repository
     */
    @Override
    public Messenger passToRepository(List<ProjectDTO> dtoList) {
        System.out.println("\n[ProjectService] passToRepository() 호출");
        System.out.println("  받은 데이터: " + dtoList.size() + "개의 DTO");
        System.out.println("  → Repository로 전달\n");

        // Repository의 printProjectInfo 메서드 호출 (파이프라인)
        Messenger messenger = projectRepository.printProjectInfo(dtoList);

        System.out.println("[ProjectService] Repository로부터 Messenger 수신 완료\n");
        return messenger;
    }
}

