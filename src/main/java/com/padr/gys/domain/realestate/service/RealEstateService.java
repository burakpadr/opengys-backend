package com.padr.gys.domain.realestate.service;

import com.padr.gys.domain.statusmanager.constant.StatusChangeOperationType;
import com.padr.gys.domain.statusmanager.context.StatusChangeHandlerContext;
import com.padr.gys.domain.statusmanager.model.StatusChangeModel;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.realestate.constant.RealEstateExceptionMessage;
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

    @Override
    public RealEstate create(RealEstate realEstate) {
        throwExceptionIfExistAssociatedWith(realEstate.getNo());

        realEstate.setIsActive(true);

        realEstatePersistencePort.save(realEstate);

        StatusChangeModel model = StatusChangeModel.builder()
                .type(StatusChangeOperationType.CREATE)
                .oldEntity(realEstate)
                .build();

        statusChangeHandlerContext.getStatusChangeHandler(RealEstate.class).handle(model);

        return realEstate;
    }

    @Override
    public RealEstate findByIdAndIsActive(Long id, Boolean isActive) {
        return realEstatePersistencePort.findByIdAndIsActive(id, isActive)
                .orElseThrow(() -> new NoSuchElementException(RealEstateExceptionMessage.REAL_ESTATE_NOT_FOUND));
    }

    @Override
    public Page<RealEstate> findByIsActive(Boolean isActive, Pageable pageable) {
        return realEstatePersistencePort.findByIsActive(isActive, pageable);
    }

    @Override
    public RealEstate update(Long id, RealEstate realEstate) {
        throwExceptionIfExistAssociatedWith(id, realEstate.getNo());

        RealEstate oldRealEstate = findByIdAndIsActive(id, true);

        oldRealEstate.setNo(realEstate.getNo());
        oldRealEstate.setCategory(realEstate.getCategory());
        oldRealEstate.setSubCategory(realEstate.getSubCategory());

        return realEstatePersistencePort.save(oldRealEstate);
    }

    @Override
    public void delete(Long id) {
        RealEstate realEstate = findByIdAndIsActive(id, true);

        realEstate.setIsActive(false);

        realEstatePersistencePort.save(realEstate);
    }

    @Override
    public void deleteAll(List<RealEstate> realEstates) {
        realEstates.stream().forEach(realEstate -> {
            realEstate.setIsActive(false);
        });

        realEstatePersistencePort.saveAll(realEstates);
    }

    @Override
    public RealEstate findByNoAndIsActive(String no, Boolean isActive) {
        return realEstatePersistencePort.findByNoAndIsActive(no, isActive)
                .orElseThrow(() -> new NoSuchElementException(RealEstateExceptionMessage.REAL_ESTATE_NOT_FOUND));
    }

    @Override
    public RealEstate changeCoverPhoto(Long realEstateId, RealEstatePhoto coverPhoto) {
        RealEstate realEstate = findByIdAndIsActive(realEstateId, true);

        realEstate.setCoverPhoto(coverPhoto);

        return realEstatePersistencePort.save(realEstate);
    }

    @Override
    public void removeCoverPhoto(Long id) {
        RealEstate realEstate = findByIdAndIsActive(id, true);

        realEstate.setCoverPhoto(null);

        realEstatePersistencePort.save(realEstate);
    }

    private void throwExceptionIfExistAssociatedWith(Long id, String no) {
        realEstatePersistencePort.findByNoAndIsActive(no, true).ifPresent(r -> {
            if (id != r.getId())
                throw new EntityExistsException(RealEstateExceptionMessage.REAL_ESTATE_ALREADY_EXIST);
        });
    }

    private void throwExceptionIfExistAssociatedWith(String no) {
        realEstatePersistencePort.findByNoAndIsActive(no, true).ifPresent(r -> {
            throw new EntityExistsException(RealEstateExceptionMessage.REAL_ESTATE_ALREADY_EXIST);
        });
    }
}
