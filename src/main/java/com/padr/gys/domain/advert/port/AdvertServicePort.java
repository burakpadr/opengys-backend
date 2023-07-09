package com.padr.gys.domain.advert.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.advert.entity.Advert;

public interface AdvertServicePort {
    
    Page<Advert> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive, Pageable pageable);

    Advert findByIdAndIsActive(Long id, Boolean isActive);

    Advert create(Advert advert);

    Advert update(Long id, Advert advert);

    void delete(Long id);
}
