package com.padr.gys.infra.inbound.common.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.padr.gys.domain.common.property.AppProperty;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final AppProperty appProperty;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(appProperty.getStorage().getBaseUrl() + "/**")
                .addResourceLocations("file:" + appProperty.getStorage().getBasePath() + "/");
    }
}
