package com.sangbepa.api.emission.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 탄소 배출량 DTO
 * Entity를 전송하기 위한 데이터 전송 객체
 * SupplyChainGHGEmissionFactors CSV 구조 반영
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmissionDTO {
    private Long id; // ID
    private String naicsCode; // 2017 NAICS Code
    private String naicsTitle; // 2017 NAICS Title
    private String ghg; // GHG (Greenhouse Gas)
    private String unit; // Unit
    private Double emissionFactorWithoutMargins; // Supply Chain Emission Factors without Margins
    private Double marginsOfEmissionFactors; // Margins of Supply Chain Emission Factors
    private Double emissionFactorWithMargins; // Supply Chain Emission Factors with Margins
    private String useeioCode; // Reference USEEIO Code
    private java.time.LocalDateTime createdAt; // 생성 시간
    private java.time.LocalDateTime updatedAt; // 수정 시간
}
