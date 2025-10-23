package com.sangbepa.api.weather.repository;

import org.springframework.stereotype.Repository;
import com.sangbepa.api.weather.domain.WeatherDTO;
import com.sangbepa.api.common.domain.Messenger;
import java.util.List;

/**
 * Weather Repository 구현체
 * DTO를 직접 사용하여 터미널에 출력하는 계층
 */
@Repository
public class WeatherRepositoryImpl implements WeatherRepository {

    /**
     * 날씨 정보를 터미널에 출력하고 Messenger 반환
     * 
     * @param weatherList 출력할 날씨 DTO 리스트
     * @return 출력 결과를 담은 Messenger
     */
    @Override
    public Messenger printWeatherInfo(List<WeatherDTO> weatherList) {
        System.out.println("\n[WeatherRepository] 터미널 출력 시작");
        System.out.println("==========================================");
        System.out.println("  받은 데이터: " + weatherList.size() + "개의 DTO");
        System.out.println("==========================================\n");

        for (int i = 0; i < weatherList.size(); i++) {
            WeatherDTO dto = weatherList.get(i);

            // 터미널 출력 (DTO의 getter 직접 사용)
            System.out.println("┌─────────────────────────────────────────┐");
            System.out.println("│  🌤️  날씨 정보 #" + (i + 1));
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  📅 일시        : " + dto.getDate());
            System.out.println("│  🌡️  평균 기온  : " + formatTemperature(dto.getAvgTemperature()));
            System.out.println("│  ⬆️  최고 기온  : " + formatTemperature(dto.getMaxTemperature()) +
                    (dto.getMaxTemperatureTime() != null ? " (" + dto.getMaxTemperatureTime() + ")" : ""));
            System.out.println("│  ⬇️  최저 기온  : " + formatTemperature(dto.getMinTemperature()) +
                    (dto.getMinTemperatureTime() != null ? " (" + dto.getMinTemperatureTime() + ")" : ""));
            System.out.println("│  📊 일교차      : " + formatTemperature(dto.getTemperatureRange()));
            System.out.println("│  💧 강수량      : " + formatPrecipitation(dto.getPrecipitation()));
            System.out.println("└─────────────────────────────────────────┘\n");
        }

        System.out.println("==========================================");
        System.out.println("[WeatherRepository] ✓ " + weatherList.size() + "개 출력 완료");
        System.out.println("==========================================\n");

        // Messenger 생성 및 반환
        Messenger messenger = new Messenger();
        messenger.setCode(0);
        messenger.setMessage("Repository: " + weatherList.size() + "개의 날씨 정보를 성공적으로 출력했습니다");
        return messenger;
    }

    /**
     * 온도 포맷팅
     */
    private String formatTemperature(Double temp) {
        if (temp == null) {
            return "데이터 없음";
        }
        return temp + "℃";
    }

    /**
     * 강수량 포맷팅
     */
    private String formatPrecipitation(Double precip) {
        if (precip == null || precip == 0.0) {
            return "없음";
        }
        return precip + "mm";
    }
}
