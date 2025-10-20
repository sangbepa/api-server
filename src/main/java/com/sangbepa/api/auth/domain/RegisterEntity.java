package com.sangbepa.api.auth.domain;

/**
 * Register Entity - 회원가입 Entity
 */
public class RegisterEntity {
    private String email;
    private String password;
    private String name;

    // 기본 생성자
    public RegisterEntity() {
    }

    // 전체 필드 생성자
    public RegisterEntity(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Getter 메서드
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    // Setter 메서드
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}