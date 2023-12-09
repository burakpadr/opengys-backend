package com.padr.gys.domain.common.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.padr.gys.domain.common.model.property.ImagePropertyModel;
import com.padr.gys.domain.common.model.property.StoragePropertyModel;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperty {

    private StoragePropertyModel storage;
    private ImagePropertyModel image;
}
