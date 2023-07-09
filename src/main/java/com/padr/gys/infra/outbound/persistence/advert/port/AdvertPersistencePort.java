package com.padr.gys.infra.outbound.persistence.advert.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.advert.entity.Advert;

public interface AdvertPersistencePort {
    
    Page<Advert> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive, Pageable pageable);

    Optional<Advert> findByIdAndIsActive(Long id, Boolean isActive);

    Advert save(Advert advert);
}
