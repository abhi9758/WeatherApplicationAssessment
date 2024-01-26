package com.assessment.WeatherApp.controller;

import static org.mockito.Mockito.when;

import com.assessment.WeatherApp.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {WeatherController.class})
@ExtendWith(SpringExtension.class)
class WeatherControllerDiffblueTest {
    @Autowired
    private WeatherController weatherController;

    @MockBean
    private WeatherService weatherService;

    /**
     * Method under test: {@link WeatherController#getForecastSummary(String)}
     */
    @Test
    void testGetForecastSummary() throws Exception {
        // Arrange
        when(weatherService.getForecastSummary(Mockito.<String>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/weather/forecast-summary")
                .param("city", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(weatherController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link WeatherController#getHourlyForecast(String)}
     */
    @Test
    void testGetHourlyForecast() throws Exception {
        // Arrange
        when(weatherService.getHourlyForecast(Mockito.<String>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/weather/hourly-forecast")
                .param("city", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(weatherController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link WeatherController#getForecastSummaryByCoordinates(String, String)}
     */
    @Test
    void testGetForecastSummaryByCoordinates() throws Exception {
        // Arrange
        when(weatherService.getForecastSummaryByCoordinates(Mockito.<String>any(), Mockito.<String>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/weather/forecast-summary-by-coordinates")
                .param("latitude", "foo")
                .param("longitude", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(weatherController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
