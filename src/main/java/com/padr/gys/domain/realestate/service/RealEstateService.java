package com.padr.gys.domain.realestate.service;

import com.padr.gys.domain.statusmanager.constant.StatusChangeReportType;
import com.padr.gys.domain.statusmanager.model.StatusChangeReportModel;
import com.padr.gys.domain.statusmanager.reporter.StatusChangeReporter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.exception.RealEstateAlreadyExistException;
import com.padr.gys.domain.realestate.exception.RealEstateNotFoundException;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RealEstateService implements RealEstateServicePort {

    private final RealEstatePersistencePort realEstatePersistencePort;

    private final StatusChangeReporter statusChangeReporter;

    @Override
    public RealEstate create(RealEstate realEstate) {
        throwExceptionIfExistAssociatedWith(realEstate.getNo());

        realEstate.setIsActive(true);

        realEstatePersistencePort.save(realEstate);

        statusChangeReporter.getCreateRealEstateReporter().submit(
                StatusChangeReportModel.<RealEstate>builder()
                .type(StatusChangeReportType.CREATE)
                .oldEntity(realEstate)
                .build());

        return realEstate;
    }

    @Override
    public RealEstate findByIdAndIsActive(Long id, Boolean isActive) {
        return realEstatePersistencePort.findByIdAndIsActive(id, isActive)
                .orElseThrow(() -> new RealEstateNotFoundException(id));
    }

    @Override
    public Page<RealEstate> findByIsActive(Boolean isActive, Pageable pageable) {
        return realEstatePersistencePort.findByIsActive(isActive, pageable);
    }

    @Override
    public RealEstate update(Long id, RealEstate realEstate) {
        throwExceptionIfExistAssociatedWith(realEstate.getNo());

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
    public RealEstate findByNoAndIsActive(String no, Boolean isActive) {
        return realEstatePersistencePort.findByNoAndIsActive(no, isActive)
                .orElseThrow(() -> new RealEstateNotFoundException(no));
    }

    private void throwExceptionIfExistAssociatedWith(String no) {
        realEstatePersistencePort.findByNoAndIsActive(no, true).ifPresent(r -> {
            throw new RealEstateAlreadyExistException(r.getNo());
        });
    }
}
