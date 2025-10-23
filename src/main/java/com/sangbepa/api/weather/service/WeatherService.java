package com.sangbepa.api.weather.service;

import com.sangbepa.api.weather.domain.WeatherDTO;
import com.sangbepa.api.common.domain.Messenger;
import java.util.List;

/**
 * Weather Service 인터페이스
 * Repository로 파이프라인 연결
 */
public interface WeatherService {

    /**
     * BULK - CSV 데이터 일괄 처리를 위한 파이프라인
     */
    Messenger passToRepository(List<WeatherDTO> dtoList);
}
