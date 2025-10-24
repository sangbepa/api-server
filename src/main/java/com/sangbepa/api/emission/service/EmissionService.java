package com.sangbepa.api.emission.service;

import com.sangbepa.api.emission.domain.EmissionDTO;
import com.sangbepa.api.common.domain.Messenger;
import java.util.List;

public interface EmissionService {

    Messenger passToRepository(List<EmissionDTO> dtoList);

    Messenger save(EmissionDTO dto);

    Messenger saveAll(List<EmissionDTO> dtoList);

    Messenger findAll();

    Messenger findById(Integer id);

    Messenger update(Integer id, EmissionDTO dto);

    Messenger delete(Integer id);

    Messenger printEmissionInfo(List<EmissionDTO> emissions);

    Messenger deleteAll();

    List<EmissionDTO> getAllEmissions();

    List<EmissionDTO> findBySite(String site);

    EmissionDTO findDtoById(Integer id);
}
