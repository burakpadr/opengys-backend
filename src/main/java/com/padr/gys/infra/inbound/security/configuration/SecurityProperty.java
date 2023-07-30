package com.padr.gys.infra.inbound.security.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityProperty {

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.expiresInDays}")
    private Integer jwtExpiresInDays;
}
