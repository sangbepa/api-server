package com.sangbepa.api.auth.controller;

import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RegisterController {

    @PostConstruct
    public void printTop5Passengers() {
        System.out.println("=".repeat(80));
        System.out.println("타이타닉 승객 상위 5명 정보");
        System.out.println("=".repeat(80));

        try {
            // CSV 파일을 List<Map> 형태로 읽기
            List<Map<String, String>> passengerList = readCsvToList("static/csv/train.csv", 5);

            // List에 저장된 데이터 출력
            for (int i = 0; i < passengerList.size(); i++) {
                Map<String, String> passenger = passengerList.get(i);

                System.out.println("\n[승객 " + (i + 1) + "]");
                System.out.println("  PassengerId: " + passenger.get("PassengerId"));
                System.out.println("  Survived: " + (passenger.get("Survived").equals("1") ? "생존" : "사망"));
                System.out.println("  Pclass: " + passenger.get("Pclass") + "등석");
                System.out.println("  Name: " + passenger.get("Name"));
                System.out.println("  Sex: " + passenger.get("Sex"));
                System.out.println("  Age: " + passenger.get("Age"));
                System.out.println("  Fare: " + passenger.get("Fare"));
            }

            System.out.println("\n" + "=".repeat(80));
            System.out.println("총 " + passengerList.size() + "명의 승객 정보를 출력했습니다.");
            System.out.println("=".repeat(80));

        } catch (Exception e) {
            System.err.println("CSV 파일을 읽는 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * CSV 파일을 읽어서 List<Map<String, String>> 형태로 반환
     * 
     * @param filePath CSV 파일 경로
     * @param limit    읽을 레코드 수 (0이면 전체)
     * @return 승객 정보 리스트
     */
    private List<Map<String, String>> readCsvToList(String filePath, int limit) throws Exception {
        List<Map<String, String>> dataList = new ArrayList<>();

        // CSV 파일 읽기
        ClassPathResource resource = new ClassPathResource(filePath);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));

        // CSVParser 생성 (헤더 자동 인식)
        @SuppressWarnings("deprecation")
        CSVFormat format = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreHeaderCase(true)
                .setTrim(true)
                .build();
        CSVParser csvParser = format.parse(reader);

        int count = 0;
        for (CSVRecord record : csvParser) {
            if (limit > 0 && count >= limit)
                break;

            // 각 레코드를 Map으로 변환
            Map<String, String> passengerMap = new HashMap<>();
            passengerMap.put("PassengerId", record.get("PassengerId"));
            passengerMap.put("Survived", record.get("Survived"));
            passengerMap.put("Pclass", record.get("Pclass"));
            passengerMap.put("Name", record.get("Name"));
            passengerMap.put("Sex", record.get("Sex"));
            passengerMap.put("Age", record.get("Age"));
            passengerMap.put("SibSp", record.get("SibSp"));
            passengerMap.put("Parch", record.get("Parch"));
            passengerMap.put("Ticket", record.get("Ticket"));
            passengerMap.put("Fare", record.get("Fare"));
            passengerMap.put("Cabin", record.get("Cabin"));
            passengerMap.put("Embarked", record.get("Embarked"));

            // List에 추가
            dataList.add(passengerMap);
            count++;
        }

        csvParser.close();
        reader.close();

        return dataList;
    }

}
