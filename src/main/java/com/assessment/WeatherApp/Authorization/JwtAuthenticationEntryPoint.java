
package com.assessment.WeatherApp.Authorization;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

// Marking the class as a Spring component for automatic detection during component scanning
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // Implementation of the commence method from AuthenticationEntryPoint interface
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        // Setting HTTP status code to 401 (Unauthorized)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Obtaining a PrintWriter from the response to write the error message to the client
        PrintWriter writer = response.getWriter();

        // Writing the error message to the client, indicating access is denied along with an optional error message
        writer.println("Access Denied !! " + authException.getMessage());
    }
}
