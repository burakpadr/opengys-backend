package com.padr.gys.domain.advertplace.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.advertplace.entity.elasticsearch.AdvertPlaceElasticsearch;
import com.padr.gys.domain.advertplace.entity.persistence.AdvertPlace;
import com.padr.gys.domain.advertplace.exception.AdvertPlaceNotFoundException;
import com.padr.gys.domain.advertplace.port.AdvertPlaceServicePort;
import com.padr.gys.infra.outbound.elasticsearch.advertplace.port.AdvertPlaceElasticsearchPort;
import com.padr.gys.infra.outbound.persistence.advertplace.port.AdvertPlacePersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdvertPlaceService implements AdvertPlaceServicePort {

    private final AdvertPlacePersistencePort advertPlacePersistencePort;
    private final AdvertPlaceElasticsearchPort advertPlaceElasticsearchPort;

    @Override
    public Page<AdvertPlace> findByIsActive(Boolean isActive, Pageable pageable) {
        return advertPlacePersistencePort.findByIsActive(isActive, pageable);
    }

    @Override
    public Page<AdvertPlaceElasticsearch> search(String searchTerm, Pageable pageable) {
        return advertPlaceElasticsearchPort.findByName(searchTerm, pageable);
    }

    @Override
    public AdvertPlace findByIdAndIsActive(Long id, Boolean isActive) {
        return advertPlacePersistencePort.findByIdAndIsActive(id, isActive)
                .orElseThrow(() -> new AdvertPlaceNotFoundException(id));
    }

    @Override
    public AdvertPlace create(AdvertPlace advertPlace) {
        advertPlace.setIsActive(true);

        advertPlacePersistencePort.save(advertPlace);
        advertPlaceElasticsearchPort.save(AdvertPlaceElasticsearch.of(advertPlace));

        return advertPlace;
    }

    @Override
    public AdvertPlace update(Long id, AdvertPlace advertPlace) {
        AdvertPlace oldAdvertPlace = findByIdAndIsActive(id, true);
        AdvertPlaceElasticsearch advertPlaceElasticsearch = advertPlaceElasticsearchPort.findByRowId(id);

        oldAdvertPlace.setName(advertPlace.getName());
        advertPlacePersistencePort.save(oldAdvertPlace);

        advertPlaceElasticsearch.updateFrom(oldAdvertPlace);
        advertPlaceElasticsearchPort.save(advertPlaceElasticsearch);

        return oldAdvertPlace;
    }

    @Override
    public void delete(Long id) {
        AdvertPlace advertPlace = findByIdAndIsActive(id, true);

        advertPlace.setIsActive(false);

        advertPlacePersistencePort.save(advertPlace);
        advertPlaceElasticsearchPort.deleteAllByRowId(id);
    }
}
