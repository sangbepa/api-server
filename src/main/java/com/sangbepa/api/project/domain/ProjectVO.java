package com.sangbepa.api.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Project VO - Repository에서 Service로 데이터 전달용
 * 불변 객체 (Immutable Object)
 */
@Getter
@AllArgsConstructor
public class ProjectVO {
    private final String site; // 사이트명
    private final Double scope1Tco2e; // Scope 1 배출량 (tCO2e)
    private final Double scope2Tco2e; // Scope 2 배출량 (tCO2e)
    private final Double totalScope12Tco2e; // Scope 1+2 총 배출량 (tCO2e)
    private final Double scope3Tco2e; // Scope 3 배출량 (tCO2e)
}
