package com.sangbepa.api.emission.repository;

import com.sangbepa.api.common.domain.Messenger;
import com.sangbepa.api.emission.domain.EmissionDTO;
import java.util.List;

public interface EmissionRepository {

    Messenger passToRepository(List<EmissionDTO> dtoList);

    Messenger save(EmissionDTO dto);

    Messenger saveAll(List<EmissionDTO> dtoList);

    Messenger findAll();

    Messenger findById(Integer id);

    Messenger update(Integer id, EmissionDTO dto);

    Messenger delete(Integer id);

    Messenger printEmissionInfo(List<EmissionDTO> emissions);

    void deleteAll();

    List<EmissionDTO> getAllEmissions();
}
