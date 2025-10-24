package com.sangbepa.api.calculator.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 계산기 DTO - Controller <-> Service 간 데이터 전달용
 * HTML 계산기의 기능을 반영한 데이터 구조
 */
@Data // Getter, Setter, toString, equals, hashCode 자동 생성
@NoArgsConstructor // 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드 생성자 자동 생성
public class CalculaterDTO {
    private Double num1; // 첫 번째 숫자 (null 허용)
    private Double num2; // 두 번째 숫자 (null 허용)
    private String operator; // 연산자 (+, -, *, /)
    private Double result; // 계산 결과
    private String display; // 화면에 표시될 값
    private String error; // 에러 메시지
    private Boolean isError; // 에러 여부
}