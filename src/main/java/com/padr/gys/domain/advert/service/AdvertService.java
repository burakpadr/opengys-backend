package com.padr.gys.domain.advert.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.advert.exception.AdvertNotFoundException;
import com.padr.gys.domain.advert.port.AdvertServicePort;
import com.padr.gys.infra.outbound.persistence.advert.port.AdvertPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdvertService implements AdvertServicePort {

    private final AdvertPersistencePort advertPersistencePort;

    @Override
    public Page<Advert> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive, Pageable pageable) {
        return advertPersistencePort.findByRealEstateIdAndIsActive(realEstateId, isActive, pageable);
    }

    @Override
    public Advert findByIdAndIsActive(Long id, Boolean isActive) {
        return advertPersistencePort.findByIdAndIsActive(id, isActive)
                .orElseThrow(() -> new AdvertNotFoundException(id));
    }

    @Override
    public Advert create(Advert advert) {
        advert.setIsActive(true);

        return advertPersistencePort.save(advert);
    }

    @Override
    public Advert update(Long id, Advert advert) {
        Advert oldAdvert = findByIdAndIsActive(id, true);

        oldAdvert.setAdvertPlace(advert.getAdvertPlace());
        oldAdvert.setStartDate(advert.getStartDate());
        oldAdvert.setEndDate(advert.getEndDate());
        oldAdvert.setPrice(advert.getPrice());
        oldAdvert.setIsPublished(advert.getIsPublished());

        return advertPersistencePort.save(oldAdvert);
    }

    @Override
    public void delete(Long id) {
        Advert advert = findByIdAndIsActive(id, true);

        advert.setIsActive(false);

        advertPersistencePort.save(advert);
    }
}
