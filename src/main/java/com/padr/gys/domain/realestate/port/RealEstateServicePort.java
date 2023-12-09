package com.padr.gys.domain.realestate.port;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.entity.RealEstatePhoto;

public interface RealEstateServicePort {
    
    RealEstate create(RealEstate realEstate);

    RealEstate findByIdAndIsActive(Long id, Boolean isActive);

    RealEstate findByNoAndIsActive(String no, Boolean isActive);

    Page<RealEstate> findByIsActive(Boolean isActive, Pageable pageable);

    RealEstate update(Long id, RealEstate realEstate);

    void delete(Long id);

    void deleteAll(List<RealEstate> realEstates);

    void removeCoverPhoto(Long id);

    RealEstate changeCoverPhoto(Long realEstateId, RealEstatePhoto coverPhoto);
}
