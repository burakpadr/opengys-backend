package com.padr.gys.infra.outbound.persistence.realestate.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.realestate.entity.RealEstate;

public interface RealEstatePersistencePort {
    
    RealEstate save(RealEstate realEstate);

    Optional<RealEstate> findByIdAndIsActive(Long id, Boolean isActive);

    Page<RealEstate> findByIsActive(Boolean isActive, Pageable pageable);

    Optional<RealEstate> findByNoAndIsActive(String no, Boolean isActive);
}
