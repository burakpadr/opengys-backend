package com.padr.gys.infra.inbound.location.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CityResponse extends LocationResponse {

    public static CityResponse of(String cityName) {
        return CityResponse.builder()
                .name(cityName)
                .build();
    }
}
