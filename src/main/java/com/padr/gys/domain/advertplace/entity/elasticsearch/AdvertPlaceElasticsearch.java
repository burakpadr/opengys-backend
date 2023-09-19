package com.padr.gys.domain.advertplace.entity.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.padr.gys.domain.advertplace.entity.persistence.AdvertPlace;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(indexName = "advertplace")
public class AdvertPlaceElasticsearch {

    @Id
    private String id;

    @Field(type = FieldType.Long)
    private Long rowId;

    @Field(type = FieldType.Text)
    private String name;

    public static AdvertPlaceElasticsearch of(AdvertPlace advertPlace) {
        return AdvertPlaceElasticsearch.builder()
                .rowId(advertPlace.getId())
                .name(advertPlace.getName())
                .build();
    }

    public void updateFrom(AdvertPlace advertPlace) {
        this.name = advertPlace.getName();
    }
}
