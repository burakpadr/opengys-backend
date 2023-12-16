package com.padr.gys.infra.outbound.persistence.advert.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.advert.entity.Advert;

public interface AdvertPersistencePort {
    
    Page<Advert> findByRealEstateId(Long realEstateId, Pageable pageable);

    List<Advert> findByRealEstateId(Long realEstateId);

    Optional<Advert> findById(Long id);

    Advert save(Advert advert);
}
