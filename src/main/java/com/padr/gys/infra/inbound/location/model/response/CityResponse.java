package com.padr.gys.infra.inbound.location.model.response;

import io.github.burakpadr.turkeylocation4j.City;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CityResponse extends LocationResponse {

    public static CityResponse of(City city) {
        return CityResponse.builder()
                .name(city.getName())
                .build();
    }
}
