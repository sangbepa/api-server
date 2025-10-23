package com.sangbepa.api.weather.service;

import com.sangbepa.api.weather.domain.WeatherDTO;
import com.sangbepa.api.common.domain.Messenger;
import com.sangbepa.api.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Weather Service 구현체 - 파이프라인
 * Repository로 요청 전달 (단순 파이프라인)
 */
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    /**
     * BULK - CSV 데이터 일괄 처리
     * 파이프라인: Controller → Service → Repository
     * 실제 구현됨: Repository의 printWeatherInfo 호출
     */
    @Override
    public Messenger passToRepository(List<WeatherDTO> dtoList) {
        System.out.println("\n[WeatherService] passToRepository() 호출");
        System.out.println("  받은 데이터: " + dtoList.size() + "개의 DTO");
        System.out.println("  → Repository로 전달\n");

        // Repository의 printWeatherInfo 메서드 호출 (파이프라인)
        Messenger messenger = weatherRepository.printWeatherInfo(dtoList);

        System.out.println("[WeatherService] Repository로부터 Messenger 수신 완료\n");
        return messenger;
    }
}
