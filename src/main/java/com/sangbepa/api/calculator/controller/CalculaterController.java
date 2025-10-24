package com.sangbepa.api.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import com.sangbepa.api.calculator.service.CalculaterService;
import com.sangbepa.api.calculator.domain.CalculaterDTO;
import java.util.Map;
import java.util.HashMap;

/**
 * 계산기 컨트롤러
 * API 방식과 Thymeleaf 방식을 모두 지원
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/calculator")
public class CalculaterController {

    private final CalculaterService calculaterService;

    /**
     * 계산기 페이지 표시
     * GET /calculator/calculater
     */
    @GetMapping("/calculater")
    public String calculaterPage(Model model) {
        // 초기 상태 설정
        CalculaterDTO dto = new CalculaterDTO();
        dto.setDisplay("0");
        dto.setIsError(false);

        model.addAttribute("calculator", dto);
        return "calculator/calculater";
    }

    /**
     * 계산 API (JavaScript에서 호출)
     * GET /calculator/calculate
     */
    @GetMapping("/calculate")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> calculate(
            @RequestParam double num1,
            @RequestParam double num2,
            @RequestParam String operator) {

        Map<String, Object> response = new HashMap<>();

        try {
            CalculaterDTO dto = new CalculaterDTO();
            dto.setNum1(num1);
            dto.setNum2(num2);
            dto.setOperator(operator);

            // 계산 수행
            CalculaterDTO result = calculaterService.calculate(dto);

            if (result.getIsError()) {
                response.put("error", result.getError());
            } else {
                response.put("result", result.getResult());
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("error", "계산 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    /**
     * 계산기 폼 제출 (Thymeleaf 방식)
     * POST /calculator/calculate
     */
    @PostMapping("/calculate")
    public String calculateForm(CalculaterDTO dto, Model model) {
        try {
            CalculaterDTO result = calculaterService.calculate(dto);
            model.addAttribute("calculator", result);
        } catch (Exception e) {
            dto.setError("계산 중 오류가 발생했습니다: " + e.getMessage());
            dto.setIsError(true);
            model.addAttribute("calculator", dto);
        }

        return "calculator/calculater";
    }

    /**
     * 계산기 초기화
     * POST /calculator/clear
     */
    @PostMapping("/clear")
    public String clear(Model model) {
        CalculaterDTO dto = new CalculaterDTO();
        dto.setDisplay("0");
        dto.setIsError(false);

        model.addAttribute("calculator", dto);
        return "calculator/calculater";
    }
}
