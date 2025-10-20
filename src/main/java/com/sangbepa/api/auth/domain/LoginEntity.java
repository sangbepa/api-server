package com.sangbepa.api.auth.domain;

/**
 * Login Entity - DB 테이블과 매핑될 클래스
 */
public class LoginEntity {
    private String email;
    private String password;

    // 기본 생성자
    public LoginEntity() {
    }

    // 전체 필드 생성자
    public LoginEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter 메서드
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setter 메서드
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
