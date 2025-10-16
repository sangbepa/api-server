package com.sangbepa.api.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sangbepa.api.calculator.domain.CalculaterDTO;
import com.sangbepa.api.calculator.service.CalculaterService;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CalculaterController {

    private final CalculaterService calculaterService;

    public CalculaterController(CalculaterService calculaterService) {
        this.calculaterService = calculaterService;
    }

    /**
     * 계산기 페이지 표시
     */
    @GetMapping("/calculator/calculater")
    public String calculater() {
        return "calculator/calculater";
    }
    /**
     * 통합 계산 API 
     */
    @GetMapping("/calculator/calculate")
    @ResponseBody
    public Map<String, Object> calculate(@RequestParam double num1, 
                                         @RequestParam double num2, 
                                         @RequestParam String operator) {
        try {
            CalculaterDTO dto = new CalculaterDTO();
            dto.setNum1(num1);
            dto.setNum2(num2);
            dto.setOperator(operator);
            
            double result = calculaterService.calculate(dto);
            
            return createSuccessResponse(result);
        } catch (IllegalArgumentException | ArithmeticException e) {
            return createErrorResponse(e.getMessage());
        } catch (Exception e) {
            return createErrorResponse("계산 중 오류가 발생했습니다.");
        }
    }
    
    /**
     * 성공 응답 생성
     */
    private Map<String, Object> createSuccessResponse(double result) {
        Map<String, Object> response = new HashMap<>();
        response.put("result", result);
        return response;
    }
    
    /**
     * 에러 응답 생성
     */
    private Map<String, Object> createErrorResponse(String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", errorMessage);
        return response;
    }
    
}
