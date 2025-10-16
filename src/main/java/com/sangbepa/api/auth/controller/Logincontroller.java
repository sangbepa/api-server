package com.sangbepa.api.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sangbepa.api.auth.service.LoginService;
import com.sangbepa.api.auth.domain.LoginDTO;
import com.sangbepa.api.common.domain.Messenger;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 로그인 페이지 표시 및 로그인 처리 (GET 방식)
     * 파라미터가 없으면 → 로그인 페이지 표시
     * 파라미터가 있으면 → 로그인 처리
     */
    @GetMapping("/auth/login")
    @ResponseBody
    public Object login(@RequestParam(required = false) String email,
            @RequestParam(required = false) String password) {

        // 파라미터가 없으면 로그인 페이지 표시
        if (email == null && password == null) {
            return "auth/login";
        }

        System.out.println("=== 로그인 요청 받음 (GET) ===");
        System.out.println("email = " + email);
        System.out.println("password = " + password);

        // 파라미터 검증
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            Messenger messenger = new Messenger();
            messenger.setCode(-1);
            messenger.setMessage("이메일과 비밀번호를 입력해주세요.");
            return messenger;
        }

        // DTO 생성 및 데이터 설정
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(email);
        loginDTO.setPassword(password);

        // 서비스 호출 - Messenger 반환
        Messenger result = loginService.login(loginDTO);

        System.out.println("결과 코드: " + result.getCode());
        System.out.println("결과 메시지: " + result.getMessage());
        System.out.println("================================");

        return result;
    }
}
