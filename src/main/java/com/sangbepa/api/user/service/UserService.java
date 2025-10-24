package com.sangbepa.api.user.service;

import com.sangbepa.api.user.domain.UserDTO;
import com.sangbepa.api.common.domain.Messenger;
import java.util.List;

/**
 * User Service 인터페이스 - CRUD 기본 구조
 * Repository로 파이프라인 연결
 */
public interface UserService {

    // CREATE
    Messenger save(UserDTO dto);

    Messenger saveAll(List<UserDTO> dtoList);

    // READ
    Messenger findAll(); // query all

    Messenger findById(Integer id); // query by id

    // UPDATE
    Messenger update(Integer id, UserDTO dto);

    // DELETE
    Messenger delete(Integer id);

    // BULK - CSV 데이터 일괄 처리를 위한 파이프라인
    Messenger passToRepository(List<UserDTO> dtoList);
}
