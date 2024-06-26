package com.padr.gys.infra.inbound.common.security.configuration;

import com.padr.gys.domain.common.property.AppProperty;
import com.padr.gys.infra.inbound.common.security.filter.JwtAuthorizationFilter;

import com.padr.gys.infra.outbound.persistence.user.port.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

    private final SecurityProperty securityProperty;
    private final AppProperty appProperty;
    private final MessageSource messageSource;

    private final UserPersistencePort userPersistencePort;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> {
                    auth.requestMatchers(appProperty.getStorage().getBaseUrl() + "/**").permitAll();
                    auth.requestMatchers("/gys/api/v1/auth", "/gys/api/v1/ui-elements").permitAll()
                            .requestMatchers(HttpMethod.POST, "/gys/api/v1/users").permitAll()
                            .requestMatchers(HttpMethod.GET, "/gys/api/v1/staffs/count-deed-owner").permitAll()
                            .requestMatchers(HttpMethod.POST, "/gys/api/v1/staffs").permitAll()
                            .requestMatchers(HttpMethod.POST, "/gys/api/v1/users/create-reset-password-otp").permitAll()
                            .requestMatchers(HttpMethod.POST, "/gys/api/v1/users/validate-reset-password-otp").permitAll()
                            .requestMatchers(HttpMethod.PATCH, "/gys/api/v1/users/reset-password").permitAll()
                            .requestMatchers(HttpMethod.OPTIONS).permitAll()
                            .anyRequest().authenticated();

                })
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new JwtAuthorizationFilter(securityProperty, userPersistencePort, messageSource),
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}