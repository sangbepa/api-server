package com.sangbepa.api.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private String site; // 사이트명
    private Double scope1Tco2e; // Scope 1 배출량 (tCO2e)
    private Double scope2Tco2e; // Scope 2 배출량 (tCO2e)
    private Double totalScope12Tco2e; // Scope 1+2 총 배출량 (tCO2e)
    private Double scope3Tco2e; // Scope 3 배출량 (tCO2e)
}
