package com.sangbepa.api.weather.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeatherVO {
    private final String date; // 일시
    private final Double avgTemperature; // 평균기온(℃)
    private final Double maxTemperature; // 최고기온(℃)
    private final String maxTemperatureTime; // 최고기온시각
    private final Double minTemperature; // 최저기온(℃)
    private final String minTemperatureTime; // 최저기온시각
    private final Double temperatureRange; // 일교차
    private final Double precipitation; // 강수량(mm)
}
