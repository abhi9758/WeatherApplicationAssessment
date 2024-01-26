package com.assessment.WeatherApp.service;

import org.springframework.http.ResponseEntity;

public interface WeatherService {
    ResponseEntity<String> getForecastSummary(String city);
    ResponseEntity<String> getHourlyForecast(String city);
    ResponseEntity<String> getForecastSummaryByCoordinates(String latitude, String longitude);
}
