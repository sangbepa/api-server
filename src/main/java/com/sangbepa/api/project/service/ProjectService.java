package com.sangbepa.api.project.service;

import com.sangbepa.api.project.domain.ProjectDTO;
import com.sangbepa.api.common.domain.Messenger;
import java.util.List;

/**
 * Project Service 인터페이스
 * Repository로 파이프라인 연결
 */
public interface ProjectService {

    /**
     * BULK - CSV 데이터 일괄 처리를 위한 파이프라인
     */
    Messenger passToRepository(List<ProjectDTO> dtoList);
}
