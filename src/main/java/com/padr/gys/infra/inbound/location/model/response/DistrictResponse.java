package com.padr.gys.infra.inbound.location.model.response;

import io.github.burakpadr.turkeylocation4j.District;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class DistrictResponse extends LocationResponse {

    public static DistrictResponse of(District district) {
        return DistrictResponse.builder()
                .name(district.getName())
                .build();
    }
}
