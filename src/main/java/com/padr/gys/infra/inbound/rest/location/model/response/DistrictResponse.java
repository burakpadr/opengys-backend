package com.padr.gys.infra.inbound.rest.location.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class DistrictResponse extends LocationResponse {

    public static DistrictResponse of(String districtName) {
        return DistrictResponse.builder()
                .name(districtName)
                .build();
    }
}
