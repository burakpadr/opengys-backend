package com.padr.gys.infra.inbound.location.model.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class LocationResponse {
    
    private String name;
}