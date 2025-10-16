package com.sangbepa.api.calculator.service;

import org.springframework.stereotype.Service;
import com.sangbepa.api.calculator.domain.CalculaterDTO;

@Service
public class CalculaterService {

    /**
     * DTO를 사용한 통합 계산 메서드
     * operator 필드에 따라 적절한 연산 수행
     * 
     * @param dto 계산에 필요한 데이터 (num1, num2, operator)
     * @return 계산 결과
     * @throws IllegalArgumentException 잘못된 연산자인 경우
     * @throws ArithmeticException      0으로 나누는 경우
     */
    public double calculate(CalculaterDTO dto) {
        double num1 = dto.getNum1();
        double num2 = dto.getNum2();
        String operator = dto.getOperator();

        switch (operator) {
            case "+":
            case "plus":
                return plus(num1, num2);
            case "-":
            case "minus":
                return minus(num1, num2);
            case "*":
            case "multiply":
                return multiply(num1, num2);
            case "/":
            case "divide":
                return divide(num1, num2);
            default:
                throw new IllegalArgumentException("지원하지 않는 연산자입니다: " + operator);
        }
    }

    /**
     * 덧셈 연산 (DTO 사용)
     */
    public double plus(CalculaterDTO dto) {
        return plus(dto.getNum1(), dto.getNum2());
    }

    /**
     * 뺄셈 연산 (DTO 사용)
     */
    public double minus(CalculaterDTO dto) {
        return minus(dto.getNum1(), dto.getNum2());
    }

    /**
     * 곱셈 연산 (DTO 사용)
     */
    public double multiply(CalculaterDTO dto) {
        return multiply(dto.getNum1(), dto.getNum2());
    }

    /**
     * 나눗셈 연산 (DTO 사용)
     * 
     * @throws ArithmeticException 0으로 나누는 경우
     */
    public double divide(CalculaterDTO dto) {
        return divide(dto.getNum1(), dto.getNum2());
    }

    // 내부 계산 로직 (실제 연산 수행)

    /**
     * 덧셈 연산
     */
    private double plus(double num1, double num2) {
        return num1 + num2;
    }

    /**
     * 뺄셈 연산
     */
    private double minus(double num1, double num2) {
        return num1 - num2;
    }

    /**
     * 곱셈 연산
     */
    private double multiply(double num1, double num2) {
        return num1 * num2;
    }

    /**
     * 나눗셈 연산
     * 
     * @throws ArithmeticException 0으로 나누는 경우
     */
    private double divide(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다!");
        }
        return num1 / num2;
    }

}
