package com.padr.gys.domain.advert.service;

import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.advert.port.AdvertServicePort;
import com.padr.gys.infra.outbound.persistence.advert.port.AdvertPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdvertService implements AdvertServicePort {

    private final AdvertPersistencePort advertPersistencePort;

    private final MessageSource messageSource;

    @Override
    public Page<Advert> findByRealEstateId(Long realEstateId, Pageable pageable) {
        return advertPersistencePort.findByRealEstateId(realEstateId, pageable);
    }

    @Override
    public Advert findById(Long id) {
        return advertPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("advert.not-found", null, LocaleContextHolder.getLocale())));
    }

    @Override
    public Advert create(Advert advert) {
        return advertPersistencePort.save(advert);
    }

    @Override
    public Advert update(Advert oldAdvert, Advert newAdvert) {
        oldAdvert.setAdvertPlace(newAdvert.getAdvertPlace());
        oldAdvert.setStartDate(newAdvert.getStartDate());
        oldAdvert.setEndDate(newAdvert.getEndDate());
        oldAdvert.setPrice(newAdvert.getPrice());
        oldAdvert.setIsPublished(newAdvert.getIsPublished());

        return advertPersistencePort.save(oldAdvert);
    }

    @Override
    public void delete(Long id) {
        Advert advert = findById(id);

        advert.setIsDeleted(true);

        advertPersistencePort.save(advert);
    }
}
