package com.padr.gys.domain.advertplace.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.advertplace.constant.AdvertPlaceExceptionMessage;
import com.padr.gys.domain.advertplace.entity.AdvertPlace;
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
    public Page<AdvertPlace> search(String searchTerm, Pageable pageable) {
        return advertPlacePersistencePort.findBySearchTerm(searchTerm, pageable);
    }

    @Override
    public List<AdvertPlace> findByIsActive(Boolean isActive) {
        return advertPlacePersistencePort.findByIsActive(isActive);
    }

    @Override
    public AdvertPlace findByIdAndIsActive(Long id, Boolean isActive) {
        return advertPlacePersistencePort.findByIdAndIsActive(id, isActive)
                .orElseThrow(() -> new NoSuchElementException(AdvertPlaceExceptionMessage.ADVERT_PLACE_NOT_FOUND));
    }

    @Override
    public AdvertPlace create(AdvertPlace advertPlace) {
        advertPlace.setIsActive(true);

        advertPlacePersistencePort.save(advertPlace);

        return advertPlace;
    }

    @Override
    public AdvertPlace update(Long id, AdvertPlace advertPlace) {
        AdvertPlace oldAdvertPlace = findByIdAndIsActive(id, true);

        oldAdvertPlace.setName(advertPlace.getName());
        advertPlacePersistencePort.save(oldAdvertPlace);

        return oldAdvertPlace;
    }

    @Override
    public void delete(Long id) {
        AdvertPlace advertPlace = findByIdAndIsActive(id, true);

        advertPlace.setIsActive(false);

        advertPlacePersistencePort.save(advertPlace);
    }
}
