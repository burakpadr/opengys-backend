package com.padr.gys.infra.outbound.persistence.advertplace.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.advertplace.entity.AdvertPlace;

public interface AdvertPlacePersistencePort {
    
    Page<AdvertPlace> findByIsActive(Boolean isActive, Pageable pageable);

    List<AdvertPlace> findByIsActive(Boolean isActive);

    Optional<AdvertPlace> findByIdAndIsActive(Long id, Boolean isActive);

    Page<AdvertPlace> findBySearchTerm(String searchTerm, Pageable pageable);

    AdvertPlace save(AdvertPlace advertPlace);
}
