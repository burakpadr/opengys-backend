package com.padr.gys.infra.inbound.advertplace.model;

import com.padr.gys.domain.advertplace.entity.AdvertPlace;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdvertPlaceResponse {
    
    private Long id;
    private String name;

    public static AdvertPlaceResponse of(AdvertPlace advertPlace) {
        return AdvertPlaceResponse.builder()
                .id(advertPlace.getId())
                .name(advertPlace.getName())
                .build();
    }
}
