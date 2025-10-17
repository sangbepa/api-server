package com.sangbepa.api.auth.domain;

public class LoginDTO {
    private String email;
    private String password;

    public String getEmail() {
        return email; // 리턴타입
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
