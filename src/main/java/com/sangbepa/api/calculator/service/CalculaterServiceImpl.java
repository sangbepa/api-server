package com.sangbepa.api.calculator.service;

import org.springframework.stereotype.Service;
import com.sangbepa.api.calculator.domain.CalculaterDTO;

/**
 * 계산기 서비스 구현체
 * 초기화만 담당하는 최소한의 CRUD 구조
 */
@Service
public class CalculaterServiceImpl implements CalculaterService {

    @Override
    public CalculaterDTO calculate(CalculaterDTO dto) {
        try {
            // 입력값 검증
            if (dto.getNum1() == null || dto.getNum2() == null || dto.getOperator() == null) {
                dto.setError("입력값이 올바르지 않습니다.");
                dto.setIsError(true);
                return dto;
            }

            // 0으로 나누기 검증
            if ("/".equals(dto.getOperator()) && dto.getNum2() == 0) {
                dto.setError("0으로 나눌 수 없습니다.");
                dto.setIsError(true);
                return dto;
            }

            // 계산 수행
            double result = performCalculation(dto.getNum1(), dto.getNum2(), dto.getOperator());

            // 결과 설정
            dto.setResult(result);
            dto.setDisplay(String.valueOf(result));
            dto.setIsError(false);
            dto.setError(null);

            return dto;

        } catch (Exception e) {
            dto.setError("계산 중 오류가 발생했습니다: " + e.getMessage());
            dto.setIsError(true);
            return dto;
        }
    }

    /**
     * 실제 계산 로직
     */
    private double performCalculation(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                throw new IllegalArgumentException("지원하지 않는 연산자입니다: " + operator);
        }
    }

    /**
     * 계산기 초기화 (CRUD의 Create 역할)
     * 페이지 로드 시 초기 상태 설정
     */
    public CalculaterDTO initialize() {
        CalculaterDTO dto = new CalculaterDTO();
        dto.setDisplay("0");
        dto.setIsError(false);
        dto.setError(null);
        return dto;
    }
}
