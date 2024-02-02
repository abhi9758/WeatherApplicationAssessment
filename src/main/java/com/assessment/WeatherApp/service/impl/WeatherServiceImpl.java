package com.assessment.WeatherApp.service.impl;

import com.assessment.WeatherApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Implementation of the WeatherService interface for fetching weather data.
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    // Base URL for the weather API
    private static final String API_BASE_URL = "https://forecast9.p.rapidapi.com/rapidapi/forecast/";

    // RestTemplate instance for making HTTP requests
    private final RestTemplate restTemplate;

    // API host and key values injected from application.properties
    @Value("${X-RapidAPI-Host}")
    private String apiHost;

    @Value("${X-RapidAPI-Key}")
    private String apiKey;

    /**
     * Constructor for WeatherServiceImpl, initializing RestTemplate.
     *
     * @param restTemplateBuilder Builder for creating RestTemplate instances.
     */
    public WeatherServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * Creates HTTP headers with API key and host information.
     *
     * @return HttpHeaders instance with API key and host information.
     */
    private HttpHeaders createApiHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);
        String jwtToken = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        headers.set("Authorization", "Bearer " + jwtToken);
        return headers;
    }

    /**
     * Handles exceptions that may occur during API calls.
     *
     * @param e Exception that occurred during the API call.
     * @return ResponseEntity with appropriate error status and message.
     */
    private ResponseEntity<String> handleException(Exception e) {
        if (e instanceof HttpClientErrorException) {
            HttpClientErrorException httpClientErrorException = (HttpClientErrorException) e;
            return ResponseEntity.status(httpClientErrorException.getRawStatusCode())
                    .body(httpClientErrorException.getResponseBodyAsString());
        } else {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
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
            // Build API URL for forecast summary by city
            String apiUrl = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                    .path(city + "/summary/")
                    .build().toUriString();

            HttpHeaders headers = createApiHeaders();
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            // Make API call using RestTemplate
            return restTemplate.exchange(apiUrl, HttpMethod.GET, requestEntity, String.class, city);
        } catch (Exception e) {
            // Handle exceptions
            return handleException(e);
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
            // Build API URL for hourly forecast by city
            String apiUrl = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                    .path(city + "/hourly/")
                    .build().toUriString();

            HttpHeaders headers = createApiHeaders();
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            // Make API call using RestTemplate
            return restTemplate.exchange(apiUrl, HttpMethod.GET, requestEntity, String.class, city);
        } catch (Exception e) {
            // Handle exceptions
            return handleException(e);
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
            // Build API URL for forecast summary by coordinates
            String apiUrl = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                    .path(latitude + "/" + longitude + "/summary/")
                    .build().toUriString();

            HttpHeaders headers = createApiHeaders();
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            // Make API call using RestTemplate
            return restTemplate.exchange(apiUrl, HttpMethod.GET, requestEntity, String.class);
        } catch (Exception e) {
            // Handle exceptions
            return handleException(e);
        }
    }
}
