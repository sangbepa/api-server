package com.sangbepa.api.auth.repository;

import org.springframework.stereotype.Repository;
import com.sangbepa.api.auth.domain.RegisterEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Register Repository - 회원가입 데이터 관리
 */
@Repository
public class RegisterRepository {

    private final LoginRepository loginRepository;

    // 메모리에 저장된 회원 데이터
    private static List<RegisterEntity> registeredUsers = new ArrayList<>();

    // 생성자 주입
    public RegisterRepository(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    /**
     * 이메일 중복 확인
     * 
     * @param email 확인할 이메일
     * @return 중복이면 true, 아니면 false
     */
    public boolean existsByEmail(String email) {
        System.out.println("Repository: 이메일 중복 확인 - " + email);

        // LoginRepository를 통해 중복 확인
        boolean exists = loginRepository.findByEmail(email) != null;

        if (exists) {
            System.out.println("Repository: 이미 존재하는 이메일입니다");
        } else {
            System.out.println("Repository: 사용 가능한 이메일입니다");
        }

        return exists;
    }

    /**
     * 회원 정보 저장
     * 
     * @param registerEntity 저장할 회원 정보
     * @return 저장 성공 여부
     */
    public boolean save(RegisterEntity registerEntity) {
        System.out.println("Repository: 회원 정보 저장");
        System.out.println("이메일: " + registerEntity.getEmail());
        System.out.println("비밀번호: " + registerEntity.getPassword());
        System.out.println("이름: " + registerEntity.getName());

        try {
            registeredUsers.add(registerEntity);
            System.out.println("Repository: 회원가입 성공");
            return true;
        } catch (Exception e) {
            System.out.println("Repository: 회원가입 실패 - " + e.getMessage());
            return false;
        }
    }
}
