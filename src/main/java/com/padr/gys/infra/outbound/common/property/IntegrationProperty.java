package com.padr.gys.infra.outbound.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.padr.gys.infra.outbound.common.model.property.TcmbPropertyModel;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "integration")
@Data
public class IntegrationProperty {
    
    private TcmbPropertyModel tcmb;
}
