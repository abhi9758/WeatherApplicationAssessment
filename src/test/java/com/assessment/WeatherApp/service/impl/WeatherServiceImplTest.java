package com.assessment.WeatherApp.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WeatherServiceImpl.class})
@ExtendWith(SpringExtension.class)
class WeatherServiceImplTest {
    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private WeatherServiceImpl weatherServiceImpl;

    /**
     * Method under test: {@link WeatherServiceImpl#getForecastSummary(String)}
     */
    @Test
    void testGetForecastSummary() {
        // Arrange and Act
        ResponseEntity<String> actualForecastSummary = weatherServiceImpl.getForecastSummary("Oxford");

        // Assert
        assertEquals("Internal Server Error", actualForecastSummary.getBody());
        assertEquals(500, actualForecastSummary.getStatusCodeValue());
        assertTrue(actualForecastSummary.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link WeatherServiceImpl#getForecastSummary(String)}
     */
    @Test
    void testGetForecastSummary2() {
        // Arrange and Act
        ResponseEntity<String> actualForecastSummary = weatherServiceImpl.getForecastSummary("London");

        // Assert
        assertEquals("Internal Server Error", actualForecastSummary.getBody());
        assertEquals(500, actualForecastSummary.getStatusCodeValue());
        assertTrue(actualForecastSummary.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link WeatherServiceImpl#getHourlyForecast(String)}
     */
    @Test
    void testGetHourlyForecast() {
        // Arrange and Act
        ResponseEntity<String> actualHourlyForecast = weatherServiceImpl.getHourlyForecast("https://example.org/example");

        // Assert
        assertEquals("Internal Server Error", actualHourlyForecast.getBody());
        assertEquals(500, actualHourlyForecast.getStatusCodeValue());
        assertTrue(actualHourlyForecast.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link WeatherServiceImpl#getForecastSummaryByCoordinates(String, String)}
     */
    @Test
    void testGetForecastSummaryByCoordinates() {
        // Arrange and Act
        ResponseEntity<String> actualForecastSummaryByCoordinates = weatherServiceImpl
                .getForecastSummaryByCoordinates("Latitude", "Longitude");

        // Assert
        assertEquals("Internal Server Error", actualForecastSummaryByCoordinates.getBody());
        assertEquals(500, actualForecastSummaryByCoordinates.getStatusCodeValue());
        assertTrue(actualForecastSummaryByCoordinates.getHeaders().isEmpty());
    }
}
