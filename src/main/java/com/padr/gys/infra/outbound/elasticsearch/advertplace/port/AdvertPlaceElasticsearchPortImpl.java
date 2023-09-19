package com.padr.gys.infra.outbound.elasticsearch.advertplace.port;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.advertplace.entity.elasticsearch.AdvertPlaceElasticsearch;
import com.padr.gys.infra.outbound.elasticsearch.advertplace.repository.AdvertPlaceElasticsearchRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdvertPlaceElasticsearchPortImpl implements AdvertPlaceElasticsearchPort {

    private final AdvertPlaceElasticsearchRepository advertPlaceElasticsearchRepository;

    @Override
    public AdvertPlaceElasticsearch save(AdvertPlaceElasticsearch advertPlaceElasticsearch) {
        return advertPlaceElasticsearchRepository.save(advertPlaceElasticsearch);
    }

    @Override
    public void deleteAllByRowId(Long rowId) {
        advertPlaceElasticsearchRepository.deleteAllByRowId(rowId);
    }

    @Override
    public AdvertPlaceElasticsearch findByRowId(Long rowId) {
        return advertPlaceElasticsearchRepository.findByRowId(rowId);
    }
}
