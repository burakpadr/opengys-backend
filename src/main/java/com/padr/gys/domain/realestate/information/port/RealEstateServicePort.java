package com.padr.gys.domain.realestate.information.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.realestate.information.entity.RealEstate;

public interface RealEstateServicePort {
    
    RealEstate create(RealEstate realEstate);

    RealEstate findByIdAndIsActive(Long id, Boolean isActive);

    Page<RealEstate> findByIsActive(Boolean isActive, Pageable pageable);

    RealEstate update(Long id, RealEstate realEstate);

    void delete(Long id);
}
