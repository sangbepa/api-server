package com.sangbepa.api.emission.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmissionDTO {
    private String site;
    private Double scope1Tco2e;
    private Double scope2Tco2e;
    private Double totalScope1_2Tco2e;
    private Double scope3Tco2e;
    
}
