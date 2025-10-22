package com.sangbepa.api.weather.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.sangbepa.api.weather.domain.WeatherDTO;
import com.sangbepa.api.weather.repository.WeatherRepository;
import com.sangbepa.api.common.domain.Messenger;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * Weather Service
 * Controller에서 받은 DTO를 Repository로 넘기기만 함 (파이프라인 역할)
 */
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;

    /**
     * 애플리케이션 시작 시 날씨 CSV 파일을 자동으로 읽어서 터미널에 출력
     */
    @PostConstruct
    public void loadWeatherDataOnStartup() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("🌤️  날씨 데이터 자동 로드");
        System.out.println("=".repeat(80));

        try {
            // CSV 파일 읽기
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream("static/csv/TRAIN_weather.csv-Grid view.csv");

            if (inputStream == null) {
                System.err.println("❌ 오류: CSV 파일을 찾을 수 없습니다");
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            int lineNumber = 0;
            List<WeatherDTO> weatherList = new ArrayList<>();

            // CSV 파싱하여 WeatherDTO에 담기
            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // 헤더 라인 건너뛰기
                if (lineNumber == 1) {
                    continue;
                }

                try {
                    String[] fields = line.split(",");

                    if (fields.length >= 1) {
                        WeatherDTO dto = new WeatherDTO();

                        dto.setDate(fields[0]);
                        dto.setAvgTemperature(parseDouble(fields.length > 1 ? fields[1] : null));
                        dto.setMaxTemperature(parseDouble(fields.length > 2 ? fields[2] : null));
                        dto.setMaxTemperatureTime(fields.length > 3 ? fields[3] : null);
                        dto.setMinTemperature(parseDouble(fields.length > 4 ? fields[4] : null));
                        dto.setMinTemperatureTime(fields.length > 5 ? fields[5] : null);
                        dto.setTemperatureRange(parseDouble(fields.length > 6 ? fields[6] : null));
                        dto.setPrecipitation(parseDouble(fields.length > 7 ? fields[7] : null));

                        weatherList.add(dto);
                    }
                } catch (Exception e) {
                    // 파싱 오류 무시
                }
            }
            reader.close();

            // Repository로 전달하여 터미널에 출력
            if (weatherList.size() > 0) {
                processWeatherData(weatherList);
            } else {
                System.err.println("❌ 데이터를 추출하지 못했습니다");
            }

        } catch (Exception e) {
            System.err.println("❌ 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 날씨 DTO를 Repository로 넘기고 Messenger 반환
     * 
     * @param weatherDataList 날씨 DTO 리스트
     * @return Repository에서 반환한 Messenger
     */
    public Messenger processWeatherData(List<WeatherDTO> weatherDataList) {
        System.out.println("\n[WeatherService] DTO를 Repository로 전달");
        System.out.println("  받은 데이터: " + weatherDataList.size() + "개의 날씨 DTO");
        System.out.println("  → Repository 호출");

        // Repository로 넘기고 Messenger 받기
        Messenger messenger = weatherRepository.printWeatherInfo(weatherDataList);

        System.out.println("[WeatherService] Repository 처리 완료 - Messenger 수신\n");

        return messenger;
    }

    private Double parseDouble(String value) {
        try {
            return value == null || value.trim().isEmpty() ? null : Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
