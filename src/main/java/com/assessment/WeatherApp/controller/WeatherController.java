package com.assessment.WeatherApp.controller;

import com.assessment.WeatherApp.service.WeatherService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling weather-related endpoints.
 */
@RestController
@RequestMapping("weather")
public class WeatherController {

    private final WeatherService weatherService;

    /**
     * Constructor for WeatherController, injecting WeatherService dependency.
     *
     * @param weatherService The service responsible for fetching weather data.
     */
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Endpoint to get the forecast summary for a specific city.
     *
     * @param city The name of the city for which the forecast summary is requested.
     * @return ResponseEntity containing the forecast summary in JSON format.
     */
    @GetMapping(value = "/forecast-summary", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getForecastSummary(@RequestParam(required = true) String city) {
        return weatherService.getForecastSummary(city);
    }

    /**
     * Endpoint to get the hourly forecast for a specific city.
     *
     * @param city The name of the city for which the hourly forecast is requested.
     * @return ResponseEntity containing the hourly forecast in JSON format.
     */
    @GetMapping(value = "/hourly-forecast", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHourlyForecast(@RequestParam(required = true) String city) {
        return weatherService.getHourlyForecast(city);
    }

    /**
     * Endpoint to get the forecast summary for a specific location based on coordinates (latitude and longitude).
     *
     * @param latitude  The latitude of the location for which the forecast summary is requested.
     * @param longitude The longitude of the location for which the forecast summary is requested.
     * @return ResponseEntity containing the forecast summary in JSON format.
     */
    @GetMapping(value = "/forecast-summary-by-coordinates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getForecastSummaryByCoordinates(
            @RequestParam(required = true) String latitude,
            @RequestParam(required = true) String longitude) {
        return weatherService.getForecastSummaryByCoordinates(latitude, longitude);
    }
}
