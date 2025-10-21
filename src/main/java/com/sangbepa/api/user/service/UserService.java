package com.sangbepa.api.user.service;

import org.springframework.stereotype.Service;
import com.sangbepa.api.user.domain.UserDTO;
import com.sangbepa.api.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * User Service - MVC 연습용
 * Controller에서 받은 DTO를 Repository로 넘기기만 함
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 상위 5명 DTO를 Repository로 넘기기
     * 
     * @param top5DTOs 상위 5명의 DTO 리스트
     */
    public void passToRepository(List<UserDTO> top5DTOs) {
        System.out.println("\n[Service] DTO를 Repository로 전달");
        System.out.println("  받은 데이터: " + top5DTOs.size() + "개의 DTO");
        System.out.println("  → Repository 호출");

        // Repository로 넘기기만 함
        userRepository.printPassengerInfo(top5DTOs);

        System.out.println("[Service] Repository 처리 완료\n");
    }
}
