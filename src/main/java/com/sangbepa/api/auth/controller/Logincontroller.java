package com.sangbepa.api.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import com.sangbepa.api.auth.service.LoginService;
import com.sangbepa.api.auth.domain.LoginDTO;
import com.sangbepa.api.common.domain.Messenger;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    private final LoginService loginService; // 필드에 있습니다. - 로그인 서비스 클래스의 인스턴스를 만듬

    public LoginController(LoginService loginService) {
        this.loginService = loginService; // 생성자 주입 - 생성자를 통해서 주입하는 방법 때문에 생성자 주입이라고 함 영구적 객체
    }

    /**
     * 로그인 페이지 표시 및 로그인 처리 (GET 방식)
     * 파라미터가 없으면 → 로그인 페이지 표시
     * 파라미터가 있으면 → 로그인 처리
     */
    @GetMapping("/auth/login")
    public String login(@RequestParam(required = false) String email,
            @RequestParam(required = false) String password, Model model) {

        // 파라미터가 없으면 로그인 페이지 표시
        if (email == null && password == null) {
            return "auth/login";
        }

        System.out.println("=== 로그인 요청 받음 (GET) ===");
        System.out.println("email = " + email);
        System.out.println("password = " + password);

        // 파라미터 검증
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            Messenger messenger = new Messenger(); // 메서드를 선언한 대로 새로운 객체를 만들어서 보낸다.
            // 그러니까 왼쪽은 메모리(스텍) 영역이고 오른쪽은 힙 영역이다.
            messenger.setCode(-1);
            messenger.setMessage("이메일과 비밀번호를 입력해주세요.");
            model.addAttribute("messenger", messenger);
            return "auth/login";
        }

        // DTO 생성 및 데이터 설정
        LoginDTO loginDTO = new LoginDTO(); // 1회용 객체
        loginDTO.setEmail(email);
        loginDTO.setPassword(password);

        // 서비스 호출 - Messenger 반환
        Messenger messenger = loginService.login(loginDTO);

        System.out.println("결과 코드: " + messenger.getCode());
        System.out.println("결과 메시지: " + messenger.getMessage());
        System.out.println("================================");

        model.addAttribute("messenger", messenger);
        // 모델에 결과를 추가한다. 존슨씨가 코딩함. 이게 가장 중요한 부분이다. "messenger"는 뷰에서 사용할 변수 이름이다.
        return "auth/login"; // 뷰 이름을 반환한다.
    }
}
