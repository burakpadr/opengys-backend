package com.padr.gys.infra.outbound.elasticsearch.advertplace.port;

import com.padr.gys.domain.advertplace.entity.elasticsearch.AdvertPlaceElasticsearch;

public interface AdvertPlaceElasticsearchPort {
    
    AdvertPlaceElasticsearch save(AdvertPlaceElasticsearch advertPlaceElasticsearch);

    void deleteAllByRowId(Long rowId);

    AdvertPlaceElasticsearch findByRowId(Long rowId);
}
