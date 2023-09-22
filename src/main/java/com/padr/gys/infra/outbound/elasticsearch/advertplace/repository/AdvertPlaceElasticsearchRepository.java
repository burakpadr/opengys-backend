package com.padr.gys.infra.outbound.elasticsearch.advertplace.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.advertplace.entity.elasticsearch.AdvertPlaceElasticsearch;

@Repository
public interface AdvertPlaceElasticsearchRepository extends ElasticsearchRepository<AdvertPlaceElasticsearch, String> {

    void deleteAllByRowId(Long rowId);

    AdvertPlaceElasticsearch findByRowId(Long rowId);
}