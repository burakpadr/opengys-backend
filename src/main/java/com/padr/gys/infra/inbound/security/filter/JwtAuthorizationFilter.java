package com.padr.gys.infra.inbound.security.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import com.padr.gys.domain.user.entity.User;
import com.padr.gys.domain.user.port.UserServicePort;
import com.padr.gys.infra.inbound.common.context.UserContext;
import com.padr.gys.infra.inbound.security.configuration.SecurityProperty;
import com.padr.gys.infra.inbound.security.constant.SecurityConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    private final UserServicePort userServicePort;

    private final static Map<String, String> EXCLUDED_ENDPOINTS = Map.of(
            "/gys/api/v1/auth", HttpMethod.GET.name(),
            "/gys/api/v1/ui-elements", HttpMethod.GET.name(),
            "/gys/api/v1/staffs", HttpMethod.POST.name(),
            "/gys/api/v1/staffs/count-deed-owner", HttpMethod.GET.name());

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

                User user = userServicePort.findById(userId);

                UserContext.setUser(user);
                UserContext.setIsStaff(decodedJWT.getClaim("isStaff").asBoolean());

                request.setAttribute(SecurityConstant.LOGIN_USER_ID, userId);

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        decodedJWT.getSubject(), null, null);

                SecurityContextHolder.getContext().setAuthentication(token);
            }

            filterChain.doFilter(request, response);
        } finally {
            UserContext.removeUser();
            UserContext.removeIsStaff();
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