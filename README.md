# Weather Forecasting App

## Overview

This Weather Forecasting App provides a simple interface for fetching weather data. It integrates with a weather API to provide forecast summaries and hourly forecasts for specified cities or locations based on coordinates.

## Features

- **Get Forecast Summary by City:** Retrieve forecast summaries for a specified city.
- **Get Hourly Forecast by City:** Retrieve hourly forecasts for a specified city.
- **Get Forecast Summary by Coordinates:** Retrieve forecast summaries for a specified location based on latitude and longitude.

## Setup

1. **Clone the Repository:**
git clone https://github.com/your-username/weather-forecast-app.git

2. **Configure API Key and Host:**
- Obtain an API key and host from the weather API provider.
- Set the API key and host in the `application.properties` file.
  ```
  X-RapidAPI-Host=your-api-host
  X-RapidAPI-Key=your-api-key
  ```

3. **Build and Run:**
- Build the project using Maven:
  ```
  mvn clean install
  ```
- Run the application:
  ```
  mvn spring-boot:run
  ```

4. **Access the API Endpoints:**
- The application exposes RESTful endpoints for accessing weather data.
- Example endpoints:
  - Forecast Summary by City: `GET /forecast/summary/{city}`
  - Hourly Forecast by City: `GET /forecast/hourly/{city}`
  - Forecast Summary by Coordinates: `GET /forecast/summary/{latitude}/{longitude}`

## Technologies Used

- Java
- Spring Boot
- RestTemplate
- Maven

## Project Structure

The project follows a standard Spring Boot structure:
- `src/main/java`: Contains Java source code.
- `com.assessment.WeatherApp.service.impl`: Implementation of the WeatherService interface.
- `src/main/resources`: Contains application properties and other resources.
- `application.properties`: Configuration file for API key and host.

## API Documentation

Explore the API using Swagger. The documentation is available at:
[Swagger Documentation](http://localhost:8080/swagger-ui.html)
Make sure the application is running locally before accessing the Swagger documentation.


## Usage

1. Make HTTP requests to the provided endpoints using your preferred tool (e.g., cURL, Postman).
2. Receive JSON responses containing weather forecast data.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

## License

This project is licensed under the [MIT License](LICENSE).
