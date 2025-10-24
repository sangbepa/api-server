package com.sangbepa.api.emission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.RequiredArgsConstructor;
import com.sangbepa.api.emission.service.EmissionService;
import com.sangbepa.api.emission.domain.EmissionDTO;
import com.sangbepa.api.common.domain.Messenger;
import java.util.List;

/**
 * 탄소 배출량 데이터 관리를 위한 컨트롤러
 * CRUD 기능과 검색 기능을 제공합니다.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/emission")
public class EmissionController {

    private final EmissionService emissionService;

    /**
     * 탄소 배출량 데이터 목록 조회
     * 
     * @param site  검색할 사이트명 (선택사항)
     * @param model 뷰에 전달할 데이터 모델
     * @param flash 리다이렉트 후 전달될 메시지
     * @return 탄소 배출량 목록 페이지
     */
    @GetMapping
    public String list(@RequestParam(required = false) String site, Model model,
            @ModelAttribute("messenger") Messenger flash) {
        try {
            // 사이트명으로 검색하거나 전체 목록 조회
            List<EmissionDTO> emissions = (site != null && !site.isBlank())
                    ? emissionService.findBySite(site)
                    : emissionService.getAllEmissions();

            model.addAttribute("emissions", emissions);
            model.addAttribute("searchSite", site);
        } catch (Exception e) {
            Messenger errorMsg = new Messenger();
            errorMsg.setCode(500);
            errorMsg.setMessage("데이터 조회 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("messenger", errorMsg);
            model.addAttribute("emissions", java.util.Collections.emptyList());
        }
        return "emission/Emission";
    }

    /**
     * 새로운 탄소 배출량 데이터 저장
     * 
     * @param dto   저장할 탄소 배출량 데이터
     * @param br    유효성 검증 결과
     * @param ra    리다이렉트 시 전달할 속성
     * @param model 뷰에 전달할 데이터 모델
     * @return 저장 성공 시 목록 페이지로 리다이렉트, 실패 시 현재 페이지
     */
    @PostMapping("/save")
    public String save(EmissionDTO dto, BindingResult br,
            RedirectAttributes ra, Model model) {
        // 유효성 검증 실패 시 에러 처리
        if (br.hasErrors()) {
            model.addAttribute("emissions", emissionService.getAllEmissions());
            return "emission/Emission";
        }

        // 데이터 저장 후 결과 메시지 전달
        try {
            Messenger m = emissionService.save(dto);
            ra.addFlashAttribute("messenger", m);
        } catch (Exception e) {
            Messenger errorMsg = new Messenger();
            errorMsg.setCode(500);
            errorMsg.setMessage("저장 중 오류가 발생했습니다: " + e.getMessage());
            ra.addFlashAttribute("messenger", errorMsg);
        }
        return "redirect:/emission";
    }

    /**
     * 탄소 배출량 데이터 수정 폼 표시
     * 
     * @param id    수정할 데이터의 ID
     * @param model 뷰에 전달할 데이터 모델
     * @return 수정 폼 페이지
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        try {
            if (id == null || id <= 0) {
                Messenger errorMsg = new Messenger();
                errorMsg.setCode(400);
                errorMsg.setMessage("유효하지 않은 ID입니다.");
                model.addAttribute("messenger", errorMsg);
                return "redirect:/emission";
            }

            EmissionDTO dto = emissionService.findDtoById(id);
            model.addAttribute("emission", dto);
            return "emission/Edit";
        } catch (Exception e) {
            Messenger errorMsg = new Messenger();
            errorMsg.setCode(500);
            errorMsg.setMessage("데이터 조회 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("messenger", errorMsg);
            return "redirect:/emission";
        }
    }

    /**
     * 탄소 배출량 데이터 수정 처리
     * 
     * @param dto   수정할 탄소 배출량 데이터
     * @param br    유효성 검증 결과
     * @param ra    리다이렉트 시 전달할 속성
     * @param model 뷰에 전달할 데이터 모델
     * @return 수정 성공 시 목록 페이지로 리다이렉트, 실패 시 수정 폼 유지
     */
    @PostMapping("/update")
    public String update(@RequestParam Integer id, EmissionDTO dto, BindingResult br,
            RedirectAttributes ra, Model model) {
        // 유효성 검증 실패 시 수정 폼 유지
        if (br.hasErrors()) {
            model.addAttribute("emission", dto);
            return "emission/Edit";
        }

        // 데이터 수정 후 결과 메시지 전달
        try {
            if (id == null || id <= 0) {
                Messenger errorMsg = new Messenger();
                errorMsg.setCode(400);
                errorMsg.setMessage("유효하지 않은 ID입니다.");
                ra.addFlashAttribute("messenger", errorMsg);
                return "redirect:/emission";
            }

            Messenger m = emissionService.update(id, dto);
            ra.addFlashAttribute("messenger", m);
        } catch (Exception e) {
            Messenger errorMsg = new Messenger();
            errorMsg.setCode(500);
            errorMsg.setMessage("수정 중 오류가 발생했습니다: " + e.getMessage());
            ra.addFlashAttribute("messenger", errorMsg);
        }
        return "redirect:/emission";
    }

    /**
     * 탄소 배출량 데이터 삭제
     * 
     * @param id 삭제할 데이터의 ID
     * @param ra 리다이렉트 시 전달할 속성
     * @return 목록 페이지로 리다이렉트
     */
    @PostMapping("/delete")
    public String delete(@RequestParam Integer id, RedirectAttributes ra) {
        try {
            if (id == null || id <= 0) {
                Messenger errorMsg = new Messenger();
                errorMsg.setCode(400);
                errorMsg.setMessage("유효하지 않은 ID입니다.");
                ra.addFlashAttribute("messenger", errorMsg);
                return "redirect:/emission";
            }

            Messenger m = emissionService.delete(id);
            ra.addFlashAttribute("messenger", m);
        } catch (Exception e) {
            Messenger errorMsg = new Messenger();
            errorMsg.setCode(500);
            errorMsg.setMessage("삭제 중 오류가 발생했습니다: " + e.getMessage());
            ra.addFlashAttribute("messenger", errorMsg);
        }
        return "redirect:/emission";
    }
}
