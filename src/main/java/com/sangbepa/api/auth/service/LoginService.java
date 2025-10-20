package com.sangbepa.api.auth.service;

import org.springframework.stereotype.Service;
import com.sangbepa.api.auth.domain.LoginDTO;
import com.sangbepa.api.auth.domain.LoginEntity;
import com.sangbepa.api.common.domain.Messenger;
import com.sangbepa.api.auth.repository.LoginRepository;

/**
 * Login Service - Entity를 사용하는 로그인 처리
 */
@Service
public class LoginService {

    private final LoginRepository loginRepository; // Repository 주입

    // 생성자 주입
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    /**
     * 로그인 처리
     * 
     * @param loginDTO 로그인 정보 (DTO)
     * @return Messenger (결과 코드와 메시지)
     */
    public Messenger login(LoginDTO loginDTO) {
        System.out.println("로그인 서비스로 들어옴");
        System.out.println("DTO에서 서비스로 전달된 이메일: " + loginDTO.getEmail());
        System.out.println("DTO에서 서비스로 전달된 비밀번호: " + loginDTO.getPassword());

        // 1. Repository에서 Entity 조회
        LoginEntity loginEntity = loginRepository.findByEmail(loginDTO.getEmail());

        // 2. 사용자가 없으면 이메일 불일치 처리
        if (loginEntity == null) {
            System.out.println("Repository에서 사용자를 찾지 못함");
            Messenger messenger = new Messenger();
            messenger.setCode(1);
            messenger.setMessage("이메일 불일치");
            return messenger;
        }

        System.out.println("Entity에서 서비스로 전달된 이메일: " + loginEntity.getEmail());
        System.out.println("Entity에서 서비스로 전달된 비밀번호: " + loginEntity.getPassword());

        // 3. 비밀번호 검증
        Messenger messenger = new Messenger();

        if (loginEntity.getEmail().equals(loginDTO.getEmail())
                && loginEntity.getPassword().equals(loginDTO.getPassword())) {
            // 로그인 성공
            messenger.setCode(0);
            messenger.setMessage("로그인 성공");
        } else if (loginEntity.getEmail().equals(loginDTO.getEmail())
                && !loginEntity.getPassword().equals(loginDTO.getPassword())) {
            // 비밀번호 불일치
            messenger.setCode(2);
            messenger.setMessage("비밀번호 불일치");
        } else {
            // 이메일 불일치
            messenger.setCode(1);
            messenger.setMessage("이메일 불일치");
        }

        return messenger; // 결과를 Messenger로 반환
    }
}
