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
    public Page<Advert> findByRealEstateId(Long realEstateId, Pageable pageable) {
        return advertRepository.findByRealEstateId(realEstateId, pageable);
    }

    @Override
    public List<Advert> findByRealEstateId(Long realEstateId) {
        return advertRepository.findByRealEstateId(realEstateId);
    }

    @Override
    public Optional<Advert> findById(Long id) {
        return advertRepository.findById(id);
    }

    @Override
    public Advert save(Advert advert) {
        return advertRepository.save(advert);
    }
}
