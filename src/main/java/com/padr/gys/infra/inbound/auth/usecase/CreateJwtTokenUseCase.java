package com.padr.gys.infra.inbound.auth.usecase;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.padr.gys.domain.user.entity.User;
import com.padr.gys.domain.user.exception.UserNotFoundException;
import com.padr.gys.domain.user.port.UserServicePort;
import com.padr.gys.infra.inbound.auth.model.request.AuthRequest;
import com.padr.gys.infra.inbound.security.configuration.SecurityProperty;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class CreateJwtTokenUseCase {

    private final UserServicePort userServicePort;
    private final SecurityProperty securityProperty;
    private final PasswordEncoder passwordEncoder;

    public void execute(HttpServletResponse response, AuthRequest request) {
        User user = userServicePort.findByEmailAndIsActive(request.getUsername(), true);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new UserNotFoundException();

        Algorithm algorithm = Algorithm.HMAC256(securityProperty.getJwtSecret());

        String jwt = JWT.create()
                .withSubject(user.getId().toString())
                .withExpiresAt(Date.from(Instant.now().plus(securityProperty.getJwtExpiresInDays(), ChronoUnit.DAYS)))
                .sign(algorithm);

        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
    }
}
