package com.assessment.WeatherApp.service.impl;

import com.assessment.WeatherApp.service.WeatherService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of the WeatherService interface for fetching weather data.
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    private final RestTemplate restTemplate;

    /**
     * Constructor for WeatherServiceImpl, initializing RestTemplate.
     *
     * @param restTemplateBuilder Builder for creating RestTemplate instances.
     */
    public WeatherServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * Fetches the forecast summary for a specified city.
     *
     * @param city The name of the city for which the forecast summary is requested.
     * @return ResponseEntity containing the forecast summary in JSON format.
     */
    @Override
    public ResponseEntity<String> getForecastSummary(String city) {
        try {
            // API endpoint for forecast summary by city
            String apiUrl = "https://forecast9.p.rapidapi.com/rapidapi/forecast/" + city + "/summary/";

            // Set API headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", "3d2f451d70mshaecf49280ad377cp1ecc57jsn35e0e35ee366");
            headers.set("X-RapidAPI-Host", "forecast9.p.rapidapi.com");

            // Create HTTP request entity
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            // Make API call using RestTemplate
            return restTemplate.exchange(apiUrl, HttpMethod.GET, requestEntity, String.class, city);
        } catch (HttpClientErrorException e) {
            // Handle HttpClientErrorException (e.g., log the error, return a custom error response)
            return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            // Handle other exceptions (e.g., log the error, return a custom error response)
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    /**
     * Fetches the hourly forecast for a specified city.
     *
     * @param city The name of the city for which the hourly forecast is requested.
     * @return ResponseEntity containing the hourly forecast in JSON format.
     */
    @Override
    public ResponseEntity<String> getHourlyForecast(String city) {
        try {
            // API endpoint for hourly forecast by city
            String apiUrl = "https://forecast9.p.rapidapi.com/rapidapi/forecast/" + city + "/hourly/";

            // Set API headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", "3d2f451d70mshaecf49280ad377cp1ecc57jsn35e0e35ee366");
            headers.set("X-RapidAPI-Host", "forecast9.p.rapidapi.com");

            // Create HTTP request entity
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            // Make API call using RestTemplate
            return restTemplate.exchange(apiUrl, HttpMethod.GET, requestEntity, String.class, city);
        } catch (HttpClientErrorException e) {
            // Handle HttpClientErrorException (e.g., log the error, return a custom error response)
            return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            // Handle other exceptions (e.g., log the error, return a custom error response)
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    /**
     * Fetches the forecast summary for a specified location based on coordinates.
     *
     * @param latitude  The latitude of the location for which the forecast summary is requested.
     * @param longitude The longitude of the location for which the forecast summary is requested.
     * @return ResponseEntity containing the forecast summary in JSON format.
     */
    @Override
    public ResponseEntity<String> getForecastSummaryByCoordinates(String latitude, String longitude) {
        try {
            // API endpoint for forecast summary by coordinates
            String apiUrl = "https://forecast9.p.rapidapi.com/rapidapi/forecast/" +
                    latitude + "/" + longitude + "/summary/";

            // Set API headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", "3d2f451d70mshaecf49280ad377cp1ecc57jsn35e0e35ee366");
            headers.set("X-RapidAPI-Host", "forecast9.p.rapidapi.com");

            // Create HTTP request entity
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            // Make API call using RestTemplate
            return restTemplate.exchange(apiUrl, HttpMethod.GET, requestEntity, String.class);
        } catch (HttpClientErrorException e) {
            // Handle HttpClientErrorException (e.g., log the error, return a custom error response)
            return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            // Handle other exceptions (e.g., log the error, return a custom error response)
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
