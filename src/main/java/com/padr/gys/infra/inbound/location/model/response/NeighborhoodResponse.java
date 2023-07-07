package com.padr.gys.infra.inbound.location.model.response;

import io.github.burakpadr.turkeylocation4j.Neighborhood;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class NeighborhoodResponse extends LocationResponse {

    public static NeighborhoodResponse of(Neighborhood neighborhood) {
        return NeighborhoodResponse.builder()
                .name(neighborhood.getName())
                .build();
    }
}
