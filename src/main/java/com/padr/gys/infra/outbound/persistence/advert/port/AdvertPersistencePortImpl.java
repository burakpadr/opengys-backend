package com.padr.gys.infra.outbound.persistence.advert.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.infra.outbound.persistence.advert.repository.AdvertRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdvertPersistencePortImpl implements AdvertPersistencePort {
    
    private final AdvertRepository advertRepository;

    @Override
    public Page<Advert> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive, Pageable pageable) {
        return advertRepository.findByRealEstateIdAndIsActive(realEstateId, isActive, pageable);
    }

    @Override
    public List<Advert> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive) {
        return advertRepository.findByRealEstateIdAndIsActive(realEstateId, isActive);
    }

    @Override
    public Optional<Advert> findByIdAndIsActive(Long id, Boolean isActive) {
        return advertRepository.findByIdAndIsActive(id, isActive);
    }

    @Override
    public Advert save(Advert advert) {
        return advertRepository.save(advert);
    }
}
