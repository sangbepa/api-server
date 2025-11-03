package com.sangbepa.api.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project Entity - DB 테이블과 매핑될 클래스
 * 삼성 배출량 데이터
 */
@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {

    @Id private Long id; // 기본 키

    private String site; // 사이트명
    private Double scope1Tco2e; // Scope 1 배출량 (tCO2e)
    private Double scope2Tco2e; // Scope 2 배출량 (tCO2e)
    private Double totalScope12Tco2e; // Scope 1+2 총 배출량 (tCO2e)
    private Double scope3Tco2e; // Scope 3 배출량 (tCO2e)
}
