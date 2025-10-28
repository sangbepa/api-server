package com.sangbepa.api.emission.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 탄소 배출량 VO
 * HTML에 표시하기 위한 뷰 객체
 * SupplyChainGHGEmissionFactors CSV 구조 반영
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmissionVO {
    private Long id; // ID
    private String naicsCode; // 2017 NAICS Code
    private String naicsTitle; // 2017 NAICS Title
    private String ghg; // GHG (Greenhouse Gas)
    private String unit; // Unit
    private Double emissionFactorWithoutMargins; // Supply Chain Emission Factors without Margins
    private Double marginsOfEmissionFactors; // Margins of Supply Chain Emission Factors
    private Double emissionFactorWithMargins; // Supply Chain Emission Factors with Margins
    private String useeioCode; // Reference USEEIO Code
    private String createdAt; // 생성 시간 (문자열로 표시)
    private String updatedAt; // 수정 시간 (문자열로 표시)

    /**
     * Entity를 VO로 변환 (날짜 포맷팅 포함)
     */
    public static EmissionVO fromEntity(EmissionEntity entity) {
        return EmissionVO.builder()
                .id(entity.getId())
                .naicsCode(entity.getNaicsCode())
                .naicsTitle(entity.getNaicsTitle())
                .ghg(entity.getGhg())
                .unit(entity.getUnit())
                .emissionFactorWithoutMargins(entity.getEmissionFactorWithoutMargins())
                .marginsOfEmissionFactors(entity.getMarginsOfEmissionFactors())
                .emissionFactorWithMargins(entity.getEmissionFactorWithMargins())
                .useeioCode(entity.getUseeioCode())
                .createdAt(entity.getCreatedAt() != null ? entity.getCreatedAt().toString() : null)
                .updatedAt(entity.getUpdatedAt() != null ? entity.getUpdatedAt().toString() : null)
                .build();
    }
}
