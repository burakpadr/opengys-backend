package com.padr.gys.infra.inbound.location.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class NeighborhoodResponse extends LocationResponse {

    public static NeighborhoodResponse of(String neighborhoodName) {
        return NeighborhoodResponse.builder()
                .name(neighborhoodName)
                .build();
    }
}
