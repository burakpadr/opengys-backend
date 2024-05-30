package com.padr.gys.infra.outbound.persistence.advertplace.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.advertplace.entity.AdvertPlace;
import com.padr.gys.infra.outbound.persistence.advertplace.repository.AdvertPlaceRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class AdvertPlacePersistencePortImpl implements AdvertPlacePersistencePort {
    
    private final AdvertPlaceRepository advertPlaceRepository;

    @Override
    public Page<AdvertPlace> findAll(Pageable pageable) {
        return advertPlaceRepository.findAll(pageable);
    }

    @Override
    public Optional<AdvertPlace> findById(Long id) {
        return advertPlaceRepository.findById(id);
    }

    @Override
    public AdvertPlace save(AdvertPlace advertPlace) {
        return advertPlaceRepository.save(advertPlace);
    }

    @Override
    public Page<AdvertPlace> findBySearchTerm(String searchTerm, Pageable pageable) {
        return advertPlaceRepository.findBySearchTerm(searchTerm, pageable);
    }

    @Override
    public List<AdvertPlace> findAll() {
        return advertPlaceRepository.findAll();
    }
}
