package com.sangbepa.api.emission.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.sangbepa.api.emission.domain.EmissionDTO;
import com.sangbepa.api.emission.repository.EmissionRepository;
import com.sangbepa.api.common.domain.Messenger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmissionServiceImpl implements EmissionService {

    private final EmissionRepository emissionRepository;

    @Override
    public Messenger passToRepository(List<EmissionDTO> dtoList) {
        return emissionRepository.passToRepository(dtoList);
    }

    @Override
    public Messenger save(EmissionDTO dto) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Messenger saveAll(List<EmissionDTO> dtoList) {
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }

    @Override
    public Messenger findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Messenger findById(Integer id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Messenger update(Integer id, EmissionDTO dto) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Messenger delete(Integer id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Messenger printEmissionInfo(List<EmissionDTO> emissions) {
        throw new UnsupportedOperationException("Unimplemented method 'printEmissionInfo'");
    }

    @Override
    public Messenger deleteAll() {
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public List<EmissionDTO> getAllEmissions() {
        return emissionRepository.getAllEmissions();
    }

    @Override
    public List<EmissionDTO> findBySite(String site) {
        // 사이트명으로 검색하는 로직 (임시로 전체 목록에서 필터링)
        List<EmissionDTO> allEmissions = emissionRepository.getAllEmissions();
        return allEmissions.stream()
                .filter(emission -> emission.getSite() != null &&
                        emission.getSite().toLowerCase().contains(site.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public EmissionDTO findDtoById(Integer id) {
        // ID로 DTO를 찾는 로직 (임시로 첫 번째 항목 반환)
        List<EmissionDTO> allEmissions = emissionRepository.getAllEmissions();
        if (id != null && id > 0 && id <= allEmissions.size()) {
            return allEmissions.get(id - 1);
        }
        return new EmissionDTO(); // 빈 DTO 반환
    }
}
