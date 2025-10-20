package com.sangbepa.api.auth.service;

import org.springframework.stereotype.Service;
import com.sangbepa.api.auth.domain.RegisterDTO;
import com.sangbepa.api.auth.domain.RegisterEntity;
import com.sangbepa.api.auth.repository.RegisterRepository;
import com.sangbepa.api.common.domain.Messenger;

/**
 * Register Service - Entity를 사용하는 회원가입 처리
 */
@Service
public class RegisterService {

    private final RegisterRepository registerRepository; // Repository 주입

    // 생성자 주입
    public RegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    /**
     * 회원 가입 처리
     * 
     * @param registerDTO 가입할 회원 정보 (DTO)
     * @return Messenger (code: 0=성공, 1=이메일 중복, -1=실패)
     */
    public Messenger register(RegisterDTO registerDTO) {
        System.out.println("회원가입 서비스로 들어옴");
        System.out.println("DTO에서 전달된 이메일: " + registerDTO.getEmail());
        System.out.println("DTO에서 전달된 비밀번호: " + registerDTO.getPassword());
        System.out.println("DTO에서 전달된 이름: " + registerDTO.getName());

        Messenger messenger = new Messenger();

        // 1. 이메일 중복 확인
        if (registerRepository.existsByEmail(registerDTO.getEmail())) {
            messenger.setCode(1);
            messenger.setMessage("이미 존재하는 이메일입니다");
            System.out.println("회원가입 실패: 이메일 중복");
            return messenger;
        }

        // 2. DTO를 Entity로 변환
        RegisterEntity registerEntity = new RegisterEntity();
        registerEntity.setEmail(registerDTO.getEmail());
        registerEntity.setPassword(registerDTO.getPassword());
        registerEntity.setName(registerDTO.getName());

        System.out.println("DTO를 Entity로 변환 완료");

        // 3. Entity를 Repository에 전달하여 저장
        boolean saved = registerRepository.save(registerEntity);

        if (saved) {
            messenger.setCode(0);
            messenger.setMessage("회원가입 성공!");
            System.out.println("회원가입 성공");
        } else {
            messenger.setCode(-1);
            messenger.setMessage("회원가입 중 오류가 발생했습니다");
            System.out.println("회원가입 실패: 저장 오류");
        }

        return messenger;
    }

    /**
     * 이메일 중복 확인
     * 
     * @param email 확인할 이메일
     * @return Messenger (code: 0=사용가능, 1=중복)
     */
    public Messenger checkEmail(String email) {
        System.out.println("이메일 중복 확인 서비스: " + email);

        Messenger messenger = new Messenger();

        if (registerRepository.existsByEmail(email)) {
            messenger.setCode(1);
            messenger.setMessage("이미 사용 중인 이메일입니다");
        } else {
            messenger.setCode(0);
            messenger.setMessage("사용 가능한 이메일입니다");
        }

        return messenger;
    }
}
