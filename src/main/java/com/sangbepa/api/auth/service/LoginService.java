package com.sangbepa.api.auth.service;

import org.springframework.stereotype.Service;
import com.sangbepa.api.auth.domain.LoginDTO;
import com.sangbepa.api.auth.domain.LoginVO;
import com.sangbepa.api.common.domain.Messenger;

@Service
public class LoginService {
    public Messenger login(LoginDTO loginDTO) {
        System.out.println("로그인 서비스로 들어옴");
        System.out.println("DTO에서 서비스로 전달된 이메일: " + loginDTO.getEmail());
        System.out.println("DTO에서 서비스로 전달된 비밀번호: " + loginDTO.getPassword());

        LoginVO loginVO = new LoginVO();
        System.out.println("VO에서 서비스로 전달된 이메일: " + loginVO.getEmail());
        System.out.println("VO에서 서비스로 전달된 비밀번호: " + loginVO.getPassword());

        Messenger messenger = new Messenger();

        if (loginVO.getEmail().equals(loginDTO.getEmail())
                && loginVO.getPassword().equals(loginDTO.getPassword())) {
            // 로그인 성공
            messenger.setCode(0);
            messenger.setMessage("로그인 성공");
        } else if (loginVO.getEmail().equals(loginDTO.getEmail())
                && !loginVO.getPassword().equals(loginDTO.getPassword())) {
            // 비밀번호 불일치
            messenger.setCode(2);
            messenger.setMessage("비밀번호 불일치");
        } else {
            // 이메일 불일치
            messenger.setCode(1);
            messenger.setMessage("이메일 불일치");
        }

        return messenger;
    }
}
