package com.sangbepa.api.auth.repository;

import org.springframework.stereotype.Repository;
import com.sangbepa.api.auth.domain.LoginEntity;

/**
 * Login Repository - 하드코딩된 데이터로 사용자 조회
 */
@Repository
public class LoginRepository {

    // 하드코딩된 사용자 데이터 (DB 대신)
    private final String STORED_EMAIL = "aaa";
    private final String STORED_PASSWORD = "bbb";

    /**
     * 이메일로 사용자 조회
     * 
     * @param email 조회할 이메일
     * @return LoginEntity (없으면 null)
     */
    public LoginEntity findByEmail(String email) {
        System.out.println("Repository: 이메일로 사용자 조회 - " + email);

        // 하드코딩된 데이터와 비교
        if (STORED_EMAIL.equals(email)) {
            System.out.println("Repository: 사용자를 찾았습니다");
            LoginEntity entity = new LoginEntity();
            entity.setEmail(STORED_EMAIL);
            entity.setPassword(STORED_PASSWORD);
            return entity;
        }

        System.out.println("Repository: 사용자를 찾지 못했습니다");
        return null;
    }
}
