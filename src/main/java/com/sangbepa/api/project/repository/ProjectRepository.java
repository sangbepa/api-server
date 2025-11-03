package com.sangbepa.api.project.repository;

import com.sangbepa.api.project.domain.ProjectDTO;
import com.sangbepa.api.common.domain.Messenger;
import java.util.List;

/**
 * Project Repository 인터페이스
 */
public interface ProjectRepository {

    /**
     * 프로젝트 정보를 터미널에 출력하고 Messenger 반환
     */
    Messenger printProjectInfo(List<ProjectDTO> projectList);
}
