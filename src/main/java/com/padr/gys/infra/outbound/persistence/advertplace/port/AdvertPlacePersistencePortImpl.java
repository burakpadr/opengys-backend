package com.padr.gys.infra.outbound.persistence.advertplace.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.advertplace.entity.AdvertPlace;
import com.padr.gys.infra.outbound.persistence.advertplace.repository.AdvertPlaceRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdvertPlacePersistencePortImpl implements AdvertPlacePersistencePort {
    
    private final AdvertPlaceRepository advertPlaceRepository;

    @Override
    public Page<AdvertPlace> findByIsActive(Boolean isActive, Pageable pageable) {
        return advertPlaceRepository.findByIsActive(isActive, pageable);
    }

    @Override
    public Optional<AdvertPlace> findByIdAndIsActive(Long id, Boolean isActive) {
        return advertPlaceRepository.findByIdAndIsActive(id, isActive);
    }

    @Override
    public AdvertPlace save(AdvertPlace advertPlace) {
        return advertPlaceRepository.save(advertPlace);
    }
}
