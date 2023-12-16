package com.padr.gys.domain.advert.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.advert.entity.Advert;

public interface AdvertServicePort {
    
    Page<Advert> findByRealEstateId(Long realEstateId, Pageable pageable);

    Advert findById(Long id);

    Advert create(Advert advert);

    Advert update(Advert oldAdvert, Advert newAdvert);

    void delete(Long id);
}
