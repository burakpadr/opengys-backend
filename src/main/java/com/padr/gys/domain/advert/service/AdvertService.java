package com.padr.gys.domain.advert.service;

import com.padr.gys.domain.statusmanager.constant.StatusChangeOperationType;
import com.padr.gys.domain.statusmanager.context.StatusChangeHandlerContext;
import com.padr.gys.domain.statusmanager.model.StatusChangeModel;

import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.advert.constant.AdvertExceptionMessage;
import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.advert.port.AdvertServicePort;
import com.padr.gys.infra.outbound.persistence.advert.port.AdvertPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdvertService implements AdvertServicePort {

    private final AdvertPersistencePort advertPersistencePort;

    private final StatusChangeHandlerContext statusChangeHandlerContext;

    @Override
    public Page<Advert> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive, Pageable pageable) {
        return advertPersistencePort.findByRealEstateIdAndIsActive(realEstateId, isActive, pageable);
    }

    @Override
    public Advert findByIdAndIsActive(Long id, Boolean isActive) {
        return advertPersistencePort.findByIdAndIsActive(id, isActive)
                .orElseThrow(() -> new NoSuchElementException(AdvertExceptionMessage.ADVERT_NOT_FOUND));
    }

    @Override
    public Advert create(Advert advert) {
        advert.setIsActive(true);

        advertPersistencePort.save(advert);

        StatusChangeModel model = StatusChangeModel.builder()
                .type(StatusChangeOperationType.CREATE)
                .oldEntity(advert)
                .build();

        statusChangeHandlerContext.getStatusChangeHandler(Advert.class).handle(model);

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

        StatusChangeModel model = StatusChangeModel.builder()
                .type(StatusChangeOperationType.UPDATE)
                .oldEntity(oldAdvertCopy)
                .updatedEntity(oldAdvert)
                .build();

        statusChangeHandlerContext.getStatusChangeHandler(Advert.class).handle(model);

        return oldAdvert;
    }

    @Override
    public void delete(Long id) {
        Advert advert = findByIdAndIsActive(id, true);

        advert.setIsActive(false);

        advertPersistencePort.save(advert);

        StatusChangeModel model = StatusChangeModel.builder()
                .type(StatusChangeOperationType.DELETE)
                .oldEntity(advert)
                .build();

        statusChangeHandlerContext.getStatusChangeHandler(Advert.class).handle(model);
    }
}
