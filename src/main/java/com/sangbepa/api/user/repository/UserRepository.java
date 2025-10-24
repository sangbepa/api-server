package com.sangbepa.api.user.repository;

import com.sangbepa.api.user.domain.UserDTO;
import com.sangbepa.api.common.domain.Messenger;
import java.util.List;

/**
 * User Repository 인터페이스 - CRUD 기본 구조
 */
public interface UserRepository {

    // CREATE
    Messenger save(UserDTO dto);

    // READ
    Messenger findAll();

    Messenger findById(Integer id);

    // UPDATE
    Messenger update(Integer id, UserDTO dto);

    // DELETE
    Messenger delete(Integer id);

    // BULK - CSV 데이터 출력 (기존 기능)
    Messenger printPassengerInfo(List<UserDTO> passengers);

    Messenger saveAll(List<UserDTO> dtoList);
}
