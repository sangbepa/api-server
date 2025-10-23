package com.sangbepa.api.weather.repository;

import com.sangbepa.api.weather.domain.WeatherDTO;
import com.sangbepa.api.common.domain.Messenger;
import java.util.List;

/**
 * Weather Repository 인터페이스
 */
public interface WeatherRepository {

    /**
     * 날씨 정보를 터미널에 출력하고 Messenger 반환
     */
    Messenger printWeatherInfo(List<WeatherDTO> weatherList);
}
