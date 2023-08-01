package com.padr.gys.infra.inbound.auth.usecase;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.padr.gys.infra.inbound.auth.model.request.AuthRequest;
import com.padr.gys.infra.inbound.security.configuration.SecurityProperty;

import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class CreateJwtTokenUseCase {

    private final SecurityProperty securityProperty;

    private final AuthenticationManager authenticationManager;

    public void execute(HttpServletResponse response, AuthRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + generateJwtToken(authentication));
    }

    private String generateJwtToken(Authentication authentication) {
        Algorithm algorithm = Algorithm.HMAC256(securityProperty.getJwtSecret());

        return JWT.create()
                .withSubject(authentication.getName())
                .withExpiresAt(Date.from(Instant.now().plus(securityProperty.getJwtExpiresInDays(),
                        ChronoUnit.DAYS)))
                .sign(algorithm);

    }
}
