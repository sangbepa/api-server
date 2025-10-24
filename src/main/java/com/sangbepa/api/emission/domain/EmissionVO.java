package com.sangbepa.api.emission.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmissionVO {
    private String site;
    private Double scope1Tco2e;
    private Double scope2Tco2e;
    private Double totalScope1_2Tco2e;
    private Double scope3Tco2e;
}
