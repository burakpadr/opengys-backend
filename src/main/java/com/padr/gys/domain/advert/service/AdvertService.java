package com.padr.gys.domain.advert.service;

import com.padr.gys.domain.statusmanager.constant.StatusChangeReportType;
import com.padr.gys.domain.statusmanager.model.StatusChangeReportModel;
import com.padr.gys.domain.statusmanager.reporter.StatusChangeReporter;
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

    private final StatusChangeReporter statusChangeReporter;

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

        advertPersistencePort.save(advert);

        statusChangeReporter.getCreateAdvertReporter().submit(
                StatusChangeReportModel.<Advert>builder()
                        .type(StatusChangeReportType.CREATE)
                        .oldEntity(advert)
                        .build());

        return advert;
    }

    @Override
    public Advert update(Long id, Advert advert) {
        Advert oldAdvert = findByIdAndIsActive(id, true);

        Advert oldAdvertCopy = new Advert(oldAdvert);

        oldAdvert.setAdvertPlace(advert.getAdvertPlace());
        oldAdvert.setStartDate(advert.getStartDate());
        oldAdvert.setEndDate(advert.getEndDate());
        oldAdvert.setPrice(advert.getPrice());
        oldAdvert.setIsPublished(advert.getIsPublished());

        advertPersistencePort.save(oldAdvert);

        statusChangeReporter.getUpdateAdvertReporter().submit(
                StatusChangeReportModel.<Advert>builder()
                        .type(StatusChangeReportType.UPDATE)
                        .oldEntity(oldAdvertCopy)
                        .updatedEntity(oldAdvert)
                        .build());

        return oldAdvert;
    }

    @Override
    public void delete(Long id) {
        Advert advert = findByIdAndIsActive(id, true);

        advert.setIsActive(false);

        advertPersistencePort.save(advert);

        statusChangeReporter.getUpdateAdvertReporter().submit(
                StatusChangeReportModel.<Advert>builder()
                        .type(StatusChangeReportType.DELETE)
                        .oldEntity(advert)
                        .build());
    }
}
