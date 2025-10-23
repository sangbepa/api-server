package com.sangbepa.api.user.service;

import com.sangbepa.api.user.domain.UserDTO;
import com.sangbepa.api.common.domain.Messenger;
import com.sangbepa.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * User Service 구현체 - CRUD 파이프라인
 * Repository로 요청 전달 (단순 파이프라인)
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * CREATE - 사용자 저장
     * 파이프라인: Controller → Service → Repository
     */
    @Override
    public Messenger save(UserDTO dto) {
        System.out.println("\n[Service] save() 호출 - Repository로 전달");
        return userRepository.save(dto);
    }

    /**
     * READ - 전체 조회
     * 파이프라인: Controller → Service → Repository
     */
    @Override
    public Messenger findAll() {
        System.out.println("\n[Service] findAll() 호출 - Repository로 전달");
        return userRepository.findAll();
    }

    /**
     * READ - ID로 조회
     * 파이프라인: Controller → Service → Repository
     */
    @Override
    public Messenger findById(Integer id) {
        System.out.println("\n[Service] findById(" + id + ") 호출 - Repository로 전달");
        return userRepository.findById(id);
    }

    /**
     * UPDATE - 사용자 수정
     * 파이프라인: Controller → Service → Repository
     */
    @Override
    public Messenger update(Integer id, UserDTO dto) {
        System.out.println("\n[Service] update(" + id + ") 호출 - Repository로 전달");
        return userRepository.update(id, dto);
    }

    /**
     * DELETE - 사용자 삭제
     * 파이프라인: Controller → Service → Repository
     */
    @Override
    public Messenger delete(Integer id) {
        System.out.println("\n[Service] delete(" + id + ") 호출 - Repository로 전달");
        return userRepository.delete(id);
    }

    /**
     * BULK - CSV 데이터 일괄 처리
     * 파이프라인: Controller → Service → Repository
     * 실제 구현됨: Repository의 printPassengerInfo 호출
     */
    @Override
    public Messenger passToRepository(List<UserDTO> dtoList) {
        System.out.println("\n[Service] passToRepository() 호출");
        System.out.println("  받은 데이터: " + dtoList.size() + "개의 DTO");
        System.out.println("  → Repository로 전달\n");

        // Repository의 printPassengerInfo 메서드 호출 (파이프라인)
        Messenger messenger = userRepository.printPassengerInfo(dtoList);

        System.out.println("[Service] Repository로부터 Messenger 수신 완료\n");
        return messenger;
    }
}
