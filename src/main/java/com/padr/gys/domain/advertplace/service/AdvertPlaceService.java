package com.padr.gys.domain.advertplace.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.advertplace.entity.AdvertPlace;
import com.padr.gys.domain.advertplace.exception.AdvertPlaceNotFoundException;
import com.padr.gys.domain.advertplace.port.AdvertPlaceServicePort;
import com.padr.gys.infra.outbound.persistence.advertplace.port.AdvertPlacePersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdvertPlaceService implements AdvertPlaceServicePort {

    private final AdvertPlacePersistencePort advertPlacePersistencePort;

    @Override
    public Page<AdvertPlace> findByIsActive(Boolean isActive, Pageable pageable) {
        return advertPlacePersistencePort.findByIsActive(isActive, pageable);
    }

    @Override
    public AdvertPlace findByIdAndIsActive(Long id, Boolean isActive) {
        return advertPlacePersistencePort.findByIdAndIsActive(id, isActive)
                .orElseThrow(() -> new AdvertPlaceNotFoundException(id));
    }

    @Override
    public AdvertPlace create(AdvertPlace advertPlace) {
        advertPlace.setIsActive(true);

        return advertPlacePersistencePort.save(advertPlace);
    }

    @Override
    public AdvertPlace update(Long id, AdvertPlace advertPlace) {
        AdvertPlace oldAdvertPlace = findByIdAndIsActive(id, true);

        oldAdvertPlace.setName(advertPlace.getName());

        return advertPlacePersistencePort.save(oldAdvertPlace);
    }

    @Override
    public void delete(Long id) {
        AdvertPlace advertPlace = findByIdAndIsActive(id, true);

        advertPlace.setIsActive(false);

        advertPlacePersistencePort.save(advertPlace);
    }
}
