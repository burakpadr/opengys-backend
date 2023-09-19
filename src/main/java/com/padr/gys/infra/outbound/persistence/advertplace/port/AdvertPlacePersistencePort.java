package com.padr.gys.infra.outbound.persistence.advertplace.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.advertplace.entity.persistence.AdvertPlace;

public interface AdvertPlacePersistencePort {
    
    Page<AdvertPlace> findByIsActive(Boolean isActive, Pageable pageable);

    Optional<AdvertPlace> findByIdAndIsActive(Long id, Boolean isActive);

    AdvertPlace save(AdvertPlace advertPlace);
}
