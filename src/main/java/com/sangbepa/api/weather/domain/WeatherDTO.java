package com.sangbepa.api.weather.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDTO {
    private String date; // 일시
    private Double avgTemperature; // 평균기온(℃)
    private Double maxTemperature; // 최고기온(℃)
    private String maxTemperatureTime; // 최고기온시각
    private Double minTemperature; // 최저기온(℃)
    private String minTemperatureTime; // 최저기온시각
    private Double temperatureRange; // 일교차
    private Double precipitation; // 강수량(mm)
}
