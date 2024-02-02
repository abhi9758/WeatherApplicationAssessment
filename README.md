# Weather Forecasting App

## Overview
This Weather Forecasting App provides a simple interface for fetching weather data. It integrates with a weather API to provide forecast summaries and hourly forecasts for specified cities or locations based on coordinates.

### Features
- Get Forecast Summary by City: Retrieve forecast summaries for a specified city.
- Get Hourly Forecast by City: Retrieve hourly forecasts for a specified city.
- Get Forecast Summary by Coordinates: Retrieve forecast summaries for a specified location based on latitude and longitude.



## Setup
1. **Clone the Repository:** `git clone https://github.com/your-username/weather-forecast-app.git`
2. **Configure API Key and Host:**
    - Obtain an API key and host from the weather API provider.
    - Set the API key and host in the `application.properties` file.
      ```
      X-RapidAPI-Host=your-api-host
      X-RapidAPI-Key=your-api-key
      ```
3. **Build and Run:**
   - Build the project using Maven: `mvn clean install`
   - Run the application: `mvn spring-boot:run`

### AuthController

A REST controller for handling authentication-related requests. It includes a login endpoint (`/auth/login`) that authenticates users and returns a JWT.

#### Auth APIs:

- **Login:**
  - Endpoint: `/auth/login`
  - Method: `POST`
  - Request Body: `JwtRequest` with `email` and `password`
  - Response: Returns a `JwtResponse` containing the JWT token and user details.
  - Get Sample payload from Application.properties 

### WeatherController

A REST controller for handling weather-related endpoints. It includes endpoints to get forecast summaries, hourly forecasts, and forecasts based on coordinates.

#### Weather APIs:

- **Get Forecast Summary:**
  - Endpoint: `/weather/forecast-summary`
  - Method: `GET`
  - Query Parameter: `city`
  - Headers: `Authorization: Bearer <JWT_TOKEN>`
  - Response: Returns the forecast summary for the specified city.

- **Get Hourly Forecast:**
  - Endpoint: `/weather/hourly-forecast`
  - Method: `GET`
  - Query Parameter: `city`
  - Headers: `Authorization: Bearer <JWT_TOKEN>`
  - Response: Returns the hourly forecast for the specified city.

- **Get Forecast Summary by Coordinates:**
  - Endpoint: `/weather/forecast-summary-by-coordinates`
  - Method: `GET`
  - Query Parameters: `latitude`, `longitude`
  - Headers: `Authorization: Bearer <JWT_TOKEN>`
  - Response: Returns the forecast summary for the specified location based on coordinates.
   

## Access the API Endpoints
The application exposes RESTful endpoints for accessing weather data.

### Example endpoints:
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

## Usage
To run the WeatherApp, ensure you have the required dependencies and configurations set up. Obtain a JWT token by authenticating through the `/auth/login` endpoint and use the token for accessing weather APIs.

Make HTTP requests to the provided endpoints using your preferred tool (e.g., cURL, Postman).
Receive JSON responses containing weather forecast data.

## Contributing
Contributions are welcome! Feel free to open issues or submit pull requests.

## License
This project is licensed under the MIT License.
