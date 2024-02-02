// Package and imports for the class
package com.assessment.WeatherApp.Authorization;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Marking the class as a Spring component and extending OncePerRequestFilter
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Logger for logging messages
    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    // Autowired JwtHelper for working with JWT
    @Autowired
    private JwtHelper jwtHelper;

    // Autowired UserDetailsService for retrieving user details
    @Autowired
    private UserDetailsService userDetailsService;

    // Overriding the doFilterInternal method from OncePerRequestFilter
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Extracting the "Authorization" header from the HTTP request
        String requestHeader = request.getHeader("Authorization");
        // Example: "Bearer 2352345235sdfrsfgsdfsdf"
        logger.info(" Header :  {}", requestHeader);
        String username = null;
        String token = null;

        // Checking if the Authorization header is present and starts with "Bearer"
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            // Parsing the token from the Authorization header
            token = requestHeader.substring(7);
            try {
                // Extracting the username from the token
                username = this.jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                logger.info("Some change has been done in the token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.info("Invalid Header Value !! ");
        }

        // Checking if the username is extracted and no authentication is present in the SecurityContextHolder
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Fetching user details from the UserDetailsService
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            // Validating the token against user details
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {
                // Setting the authentication in the SecurityContextHolder
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.info("Validation fails !!");
            }
        }

        // Continuing with the filter chain
        filterChain.doFilter(request, response);
    }
}
