package com.padr.gys.domain.realestate.service;

import com.padr.gys.domain.statusmanager.constant.StatusChangeOperationType;
import com.padr.gys.domain.statusmanager.context.StatusChangeHandlerContext;
import com.padr.gys.domain.statusmanager.model.StatusChangeModel;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.entity.RealEstatePhoto;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class RealEstateService implements RealEstateServicePort {

    private final RealEstatePersistencePort realEstatePersistencePort;

    private final StatusChangeHandlerContext statusChangeHandlerContext;

    private final MessageSource messageSource;

    @Override
    public RealEstate create(RealEstate realEstate) {
        throwExceptionIfExistAssociatedWith(realEstate.getNo());

        realEstatePersistencePort.save(realEstate);

        StatusChangeModel model = StatusChangeModel.builder()
                .type(StatusChangeOperationType.CREATE)
                .oldEntity(realEstate)
                .build();

        statusChangeHandlerContext.getStatusChangeHandler(RealEstate.class).handle(model);

        return realEstate;
    }

    @Override
    public RealEstate findById(Long id) {
        return realEstatePersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("realestate.not-found", null, LocaleContextHolder.getLocale())));
    }

    @Override
    public Page<RealEstate> findAll(Pageable pageable) {
        return realEstatePersistencePort.findAll(pageable);
    }

    @Override
    public RealEstate update(Long id, RealEstate realEstate) {
        throwExceptionIfExistAssociatedWith(id, realEstate.getNo());

        RealEstate oldRealEstate = findById(id);

        oldRealEstate.setNo(realEstate.getNo());
        oldRealEstate.setCategory(realEstate.getCategory());
        oldRealEstate.setSubCategory(realEstate.getSubCategory());

        return realEstatePersistencePort.save(oldRealEstate);
    }

    @Override
    public void delete(Long id) {
        RealEstate realEstate = findById(id);

        realEstate.setIsDeleted(true);

        realEstatePersistencePort.save(realEstate);
    }

    @Override
    public void deleteAll(List<RealEstate> realEstates) {
        realEstates.stream().forEach(realEstate -> {
            realEstate.setIsDeleted(true);
        });

        realEstatePersistencePort.saveAll(realEstates);
    }

    @Override
    public RealEstate findByNo(String no) {
        return realEstatePersistencePort.findByNo(no)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("realestate.not-found", null, LocaleContextHolder.getLocale())));
    }

    @Override
    public RealEstate changeCoverPhoto(Long realEstateId, RealEstatePhoto coverPhoto) {
        RealEstate realEstate = findById(realEstateId);

        realEstate.setCoverPhoto(coverPhoto);

        return realEstatePersistencePort.save(realEstate);
    }

    @Override
    public void removeCoverPhoto(Long id) {
        RealEstate realEstate = findById(id);

        realEstate.setCoverPhoto(null);

        realEstatePersistencePort.save(realEstate);
    }

    private void throwExceptionIfExistAssociatedWith(Long id, String no) {
        realEstatePersistencePort.findByNo(no).ifPresent(r -> {
            if (id != r.getId())
                throw new EntityExistsException(
                        messageSource.getMessage("realestate.already-exist", null, LocaleContextHolder.getLocale()));
        });
    }

    private void throwExceptionIfExistAssociatedWith(String no) {
        realEstatePersistencePort.findByNo(no).ifPresent(r -> {
            throw new EntityExistsException(
                    messageSource.getMessage("realestate.already-exist", null, LocaleContextHolder.getLocale()));
        });
    }
}
