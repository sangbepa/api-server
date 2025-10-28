package com.sangbepa.api.emission.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 탄소 배출량 Entity
 * PostgreSQL 테이블과 매핑되는 엔티티 클래스
 * SupplyChainGHGEmissionFactors CSV 구조 반영
 */
@Entity
@Table(name = "emissions")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmissionEntity {

    /**
     * ID - Primary Key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 2017 NAICS Code
     */
    @Column(name = "naics_code", length = 20)
    private String naicsCode;

    /**
     * 2017 NAICS Title
     */
    @Column(name = "naics_title", length = 500)
    private String naicsTitle;

    /**
     * GHG (Greenhouse Gas)
     */
    @Column(name = "ghg", length = 50)
    private String ghg;

    /**
     * Unit
     */
    @Column(name = "unit", length = 100)
    private String unit;

    /**
     * Supply Chain Emission Factors without Margins
     */
    @Column(name = "emission_factor_without_margins")
    private Double emissionFactorWithoutMargins;

    /**
     * Margins of Supply Chain Emission Factors
     */
    @Column(name = "margins_of_emission_factors")
    private Double marginsOfEmissionFactors;

    /**
     * Supply Chain Emission Factors with Margins
     */
    @Column(name = "emission_factor_with_margins")
    private Double emissionFactorWithMargins;

    /**
     * Reference USEEIO Code
     */
    @Column(name = "useeio_code", length = 20)
    private String useeioCode;

    /**
     * 생성 시간
     */
    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;

    /**
     * 수정 시간
     */
    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;

    /**
     * 저장 전 자동으로 시간 설정
     */
    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
        updatedAt = java.time.LocalDateTime.now();
    }

    /**
     * 수정 전 자동으로 시간 설정
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = java.time.LocalDateTime.now();
    }
}
