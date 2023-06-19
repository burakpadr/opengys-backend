package com.abctech.gys.infra.inbound.model.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class LocationResponse {
    
    private String name;
}
