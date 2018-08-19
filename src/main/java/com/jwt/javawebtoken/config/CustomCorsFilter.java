package com.jwt.javawebtoken.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class CustomCorsFilter extends OncePerRequestFilter {

    @Autowired
    private Environment environment;

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", environment.getProperty("cors.request.allowed-origin"));

        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Methods", environment.getProperty("cors.request.allowed-methods"));
            response.addHeader("Access-Control-Allow-Headers", environment.getProperty("cors.request.allowed-headers"));
            response.addHeader("Access-Control-Allow-Credentials", environment.getProperty("cors.request.allowed-credentials"));
            response.addHeader("Access-Control-Max-Age", environment.getProperty("cors.request.allowed.max-age"));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}