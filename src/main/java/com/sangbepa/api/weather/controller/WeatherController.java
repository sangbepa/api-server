package com.sangbepa.api.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.sangbepa.api.weather.service.WeatherService;
import com.sangbepa.api.weather.domain.WeatherDTO;
import com.sangbepa.api.common.domain.Messenger;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 * Weather Controller
 * CSV 파일을 읽어서 WeatherDTO로 변환 후 Service에 전달
 */
@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    /**
     * 날씨 데이터 로드 API
     * GET /weather/list
     */
    @GetMapping("/weather/list")
    public String loadWeatherData(Model model) {
        System.out.println("\n==========================================");
        System.out.println("[WeatherController] GET /weather/list");
        System.out.println("==========================================");

        Messenger messenger = new Messenger();

        try {
            // 1. CSV 파일 읽기
            System.out.println("[WeatherController] 1단계: CSV 파일 읽기");
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream("static/csv/TRAIN_weather.csv-Grid view.csv");

            if (inputStream == null) {
                messenger.setCode(-1);
                messenger.setMessage("오류: CSV 파일을 찾을 수 없습니다");
                model.addAttribute("messenger", messenger);
                return "weather/list";
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            int lineNumber = 0;
            List<WeatherDTO> weatherList = new ArrayList<>();

            // 2. CSV 파싱하여 WeatherDTO에 담기
            System.out.println("[WeatherController] 2단계: CSV → WeatherDTO 변환");

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // 헤더 라인 건너뛰기
                if (lineNumber == 1) {
                    continue;
                }

                try {
                    // CSV 파싱
                    String[] fields = line.split(",");

                    if (fields.length >= 1) {
                        // WeatherDTO 생성
                        WeatherDTO dto = new WeatherDTO();

                        dto.setDate(fields[0]); // 일시
                        dto.setAvgTemperature(parseDouble(fields.length > 1 ? fields[1] : null)); // 평균기온
                        dto.setMaxTemperature(parseDouble(fields.length > 2 ? fields[2] : null)); // 최고기온
                        dto.setMaxTemperatureTime(fields.length > 3 ? fields[3] : null); // 최고기온시각
                        dto.setMinTemperature(parseDouble(fields.length > 4 ? fields[4] : null)); // 최저기온
                        dto.setMinTemperatureTime(fields.length > 5 ? fields[5] : null); // 최저기온시각
                        dto.setTemperatureRange(parseDouble(fields.length > 6 ? fields[6] : null)); // 일교차
                        dto.setPrecipitation(parseDouble(fields.length > 7 ? fields[7] : null)); // 강수량

                        weatherList.add(dto);

                        System.out.println("  → WeatherDTO #" + weatherList.size() + " 생성: " + dto.getDate());
                    }
                } catch (Exception e) {
                    System.out.println("  ⚠ 라인 " + lineNumber + " 파싱 오류 (무시): " + e.getMessage());
                }
            }
            reader.close();

            // 3. Service로 전달하고 Repository의 Messenger 받기
            if (weatherList.size() > 0) {
                System.out.println("[WeatherController] 3단계: Service로 " + weatherList.size() + "개의 WeatherDTO 전달");
                messenger = weatherService.processWeatherData(weatherList);

                System.out.println("[WeatherController] ✓ Repository의 Messenger 수신");

                // Model에 데이터 담기
                model.addAttribute("weatherList", weatherList);
                model.addAttribute("count", weatherList.size());
            } else {
                messenger.setCode(1);
                messenger.setMessage("실패: 데이터를 추출하지 못했습니다");
                System.out.println("[WeatherController] ✗ 실패");
            }

        } catch (Exception e) {
            messenger.setCode(-1);
            messenger.setMessage("오류 발생: " + e.getMessage());
            System.out.println("[WeatherController] ❌ 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // Model에 Messenger 담기
        model.addAttribute("messenger", messenger);

        System.out.println("==========================================\n");

        // View 이름 반환
        return "weather/list";
    }

    /**
     * String을 Double로 변환
     */
    private Double parseDouble(String value) {
        try {
            return value == null || value.trim().isEmpty() ? null : Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
