package com.padr.gys.infra.inbound.security.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import com.padr.gys.infra.inbound.security.configuration.SecurityProperty;
import com.padr.gys.infra.inbound.security.constant.SecurityConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final SecurityProperty securityProperty;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getServletPath().contains("/auth"))
            filterChain.doFilter(request, response);
        else {
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")) {
                try {
                    String jwtToken = authHeader.split("Bearer ")[1];
                    Algorithm algorithm = Algorithm.HMAC256(securityProperty.getJwtSecret());
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);

                    Long userId = Long.valueOf(decodedJWT.getSubject());

                    request.setAttribute(SecurityConstant.LOGIN_USER_ID, userId);

                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            decodedJWT.getSubject(), null, null);

                    SecurityContextHolder.getContext().setAuthentication(token);

                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());

                    new ObjectMapper().writeValue(response.getOutputStream(), Map.of("message", e.getMessage()));
                }
            } else {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        }
    }
}