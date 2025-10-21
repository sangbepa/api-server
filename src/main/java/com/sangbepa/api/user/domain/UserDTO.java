package com.sangbepa.api.user.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * User DTO - Controller <-> Service 간 데이터 전달용
 */
@Data                   // Getter, Setter, toString, equals, hashCode 자동 생성
@NoArgsConstructor       // 기본 생성자 자동 생성
@AllArgsConstructor      // 모든 필드 생성자 자동 생성
public class UserDTO {

    private Integer passengerId;  // 승객 ID
    private Integer survived;     // 생존 여부 (0=사망, 1=생존)
    private Integer pclass;       // 객실 등급
    private String name;          // 이름
    private String sex;           // 성별
    private Double age;           // 나이
    private Integer sibSp;        // 형제/배우자 수
    private Integer parch;        // 부모/자녀 수
    private String ticket;        // 티켓 번호
    private Double fare;          // 요금
    private String cabin;         // 객실 번호
    private String embarked;      // 탑승 항구 (C, Q, S)
}