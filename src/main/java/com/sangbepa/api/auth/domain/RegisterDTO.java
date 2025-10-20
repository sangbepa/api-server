package com.sangbepa.api.auth.domain;

public class RegisterDTO {
    private String email;
    private String password;
    private String name;

    // 기본 생성자
    public RegisterDTO() {
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
