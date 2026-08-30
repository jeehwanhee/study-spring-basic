package com.example.demo.service;

import com.example.demo.dto.WeatherResponse;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.WeatherErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class WeatherService {

    private final RestClient restClient = RestClient.create();

    public double getSeoulTemperature() {
        try {
            WeatherResponse response = restClient.get()
                    .uri("https://api.open-meteo.com/v1/forecast?latitude=37.5665&longitude=126.9780&current=temperature_2m")
                    .retrieve()
                    .body(WeatherResponse.class);

            return response.current().temperature();
        } catch (RestClientException e) {
            throw new BusinessException(WeatherErrorCode.WEATHER_API_ERROR);
        }
    }
}
