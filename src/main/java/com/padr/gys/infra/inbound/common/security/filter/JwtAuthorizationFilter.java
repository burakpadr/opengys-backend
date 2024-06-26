package com.padr.gys.infra.inbound.common.security.filter;

import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.padr.gys.domain.user.entity.User;
import com.padr.gys.infra.inbound.common.context.UserContext;
import com.padr.gys.infra.inbound.common.security.configuration.SecurityProperty;
import com.padr.gys.infra.inbound.common.security.constant.SecurityConstant;

import com.padr.gys.infra.outbound.persistence.user.port.UserPersistencePort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final SecurityProperty securityProperty;

    private final UserPersistencePort userPersistencePort;

    private final MessageSource messageSource;

    private final static Map<String, String> EXCLUDED_ENDPOINTS = Map.of(
            "/gys/api/v1/auth", HttpMethod.POST.name(),
            "/gys/api/v1/ui-elements", HttpMethod.GET.name(),
            "/gys/api/v1/staffs", HttpMethod.POST.name(),
            "/gys/api/v1/staffs/count-deed-owner", HttpMethod.GET.name(),
            "/gys/api/v1/users/create-reset-password-otp", HttpMethod.POST.name(),
            "/gys/api/v1/users/validate-reset-password-otp", HttpMethod.POST.name(),
            "/gys/api/v1/users/reset-password", HttpMethod.PATCH.name());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")) {
                String jwtToken = authHeader.split("Bearer ")[1];
                Algorithm algorithm = Algorithm.HMAC256(securityProperty.getJwtSecret());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);

                Long userId = Long.valueOf(decodedJWT.getSubject());

                User user = userPersistencePort.findById(userId)
                        .orElseThrow(() -> new NoSuchElementException(
                                messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

                UserContext.setUser(user);
                UserContext.setIsStaff(decodedJWT.getClaim("isStaff").asBoolean());
                UserContext.setIsTenant(decodedJWT.getClaim("isTenant").asBoolean());

                request.setAttribute(SecurityConstant.LOGIN_USER_ID, userId);

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        decodedJWT.getSubject(), null, null);

                SecurityContextHolder.getContext().setAuthentication(token);
            }

            filterChain.doFilter(request, response);
        } finally {
            UserContext.removeUser();
            UserContext.removeIsStaff();
            UserContext.removeIsTenant();
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestedEndpoint = request.getRequestURI();
        String httpMethod = request.getMethod();

        return EXCLUDED_ENDPOINTS
                .entrySet()
                .stream()
                .anyMatch(excludedEndpoint -> excludedEndpoint.getKey().equals(requestedEndpoint)
                        && excludedEndpoint.getValue().equals(httpMethod));
    }
}