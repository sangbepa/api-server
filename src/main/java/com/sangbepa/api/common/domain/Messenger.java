package com.sangbepa.api.common.domain;

public class Messenger {
    private int code;
    private String message;

    // 기본 생성자
    public Messenger() {
    }

    // 전체 필드 생성자
    public Messenger(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // Getter 메서드
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    // Setter 메서드
    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
// DTO가 아님 -
// 이 클래스는 단순 메세지 전달용 객체