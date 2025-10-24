package com.sangbepa.api.emission.repository;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.ArrayList;
import com.sangbepa.api.emission.domain.EmissionDTO;
import com.sangbepa.api.common.domain.Messenger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

@Repository
public class EmissionRepositoryImpl implements EmissionRepository {


    @Override
    public Messenger passToRepository(List<EmissionDTO> dtoList) {
        return new Messenger(0, "success");
    }

    @Override
    public Messenger save(EmissionDTO dto) {
        return new Messenger(0, "success");
    }

    @Override
    public Messenger saveAll(List<EmissionDTO> dtoList) {
        return new Messenger(0, "success");
    }

    @Override
    public Messenger findAll() {
        return new Messenger(0, "success");
    }

    @Override
    public Messenger findById(Integer id) {
        return new Messenger(0, "success");
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
    public void deleteAll() {
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public List<EmissionDTO> getAllEmissions() {
        List<EmissionDTO> emissions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/static/csv/emissions_samsung.csv")))) {

            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // 헤더 스킵
                }

                if (line.trim().isEmpty()) {
                    continue; // 빈 줄 스킵
                }

                String[] values = line.split(",");
                if (values.length >= 4) {
                    EmissionDTO dto = new EmissionDTO();
                    dto.setSite(values[0]);
                    dto.setScope1Tco2e(values[1].isEmpty() ? null : Double.parseDouble(values[1]));
                    dto.setScope2Tco2e(values[2].isEmpty() ? null : Double.parseDouble(values[2]));
                    dto.setTotalScope1_2Tco2e(values[3].isEmpty() ? null : Double.parseDouble(values[3]));
                    dto.setScope3Tco2e(
                            values.length > 4 && !values[4].isEmpty() ? Double.parseDouble(values[4]) : null);

                    emissions.add(dto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return emissions;
    }
}
