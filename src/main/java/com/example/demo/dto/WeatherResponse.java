package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherResponse(Current current) {
    public record Current(
            @JsonProperty("temperature_2m") double temperature
    ) {}
}
