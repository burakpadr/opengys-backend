package com.padr.gys.infra.inbound.adverplace.model;

import com.padr.gys.domain.advertplace.entity.elasticsearch.AdvertPlaceElasticsearch;
import com.padr.gys.domain.advertplace.entity.persistence.AdvertPlace;

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

    public static AdvertPlaceResponse of(AdvertPlaceElasticsearch advertPlaceElasticsearch) {
        return AdvertPlaceResponse.builder()
                .id(advertPlaceElasticsearch.getRowId())
                .name(advertPlaceElasticsearch.getName())
                .build();
    }
}
