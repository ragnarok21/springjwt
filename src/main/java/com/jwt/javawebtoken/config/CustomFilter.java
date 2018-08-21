package com.jwt.javawebtoken.config;

import com.jwt.javawebtoken.exception.TokenErrorException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE + 2)
public class CustomFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if ("OPTIONS".equals(request.getMethod()) ||  request.getServletPath().contains("/h2-console") || request.getServletPath().contains("/register/login")) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);

        }else{

            if (authHeader == null) {

                throw new TokenErrorException("Missing or invalid Authorization header");

            }else{

                if(authHeader.startsWith("Bearer ") || authHeader.startsWith("Basic ")){

                    filterChain.doFilter(request,response);

                }else{

                    throw new TokenErrorException("Missing or invalid Authorization header");

                }
            }
        }

    }
}