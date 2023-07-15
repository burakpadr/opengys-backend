package com.padr.gys.domain.rentalcontract.service;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.rentalcontract.exception.RentalContractAlreadyExistException;
import com.padr.gys.domain.rentalcontract.exception.RentalContractNotFoundException;
import com.padr.gys.domain.rentalcontract.port.RentalContractServicePort;
import com.padr.gys.domain.statusmanager.constant.StatusChangeReportType;
import com.padr.gys.domain.statusmanager.model.StatusChangeReportModel;
import com.padr.gys.domain.statusmanager.reporter.StatusChangeReporter;
import com.padr.gys.infra.outbound.persistence.rentalcontract.port.RentalContractPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalContractService implements RentalContractServicePort {

    private final RentalContractPersistencePort rentalContractPersistencePort;

    private final StatusChangeReporter statusChangeReporter;

    @Override
    public Page<RentalContract> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive, Pageable pageable) {
        return rentalContractPersistencePort.findByRealEstateIdAndIsActive(realEstateId, isActive, pageable);
    }

    @Override
    public RentalContract findByIdAndIsActive(Long id, Boolean isActive) {
        return rentalContractPersistencePort.findByIdAndIsActive(id, isActive)
                .orElseThrow(RentalContractNotFoundException::new);
    }

    @Override
    public RentalContract create(RentalContract rentalContract) {
        rentalContractPersistencePort.findByRealEstateIdAndIsActive(
                rentalContract.getRealEstate().getId(), true)
                .stream()
                .filter(RentalContract::getIsPublished)
                .findAny()
                .ifPresent(rc -> {
                    throw new RentalContractAlreadyExistException();
                });

        rentalContract.setIsActive(true);

        rentalContractPersistencePort.save(rentalContract);

        statusChangeReporter.getCreateRentalContractReporter().submit(
                StatusChangeReportModel.<RentalContract>builder()
                        .type(StatusChangeReportType.CREATE)
                        .oldEntity(rentalContract)
                        .build());

        return rentalContract;
    }

    @Override
    public RentalContract update(Long id, RentalContract rentalContract) {
        RentalContract oldRentalContract = findByIdAndIsActive(id, true);

        RentalContract oldRentalContractCopy = new RentalContract(oldRentalContract);

        oldRentalContract.setTenantTitle(rentalContract.getTenantTitle());
        oldRentalContract.setTenantIdentityNumber(rentalContract.getTenantIdentityNumber());
        oldRentalContract.setMonthlyRentFee(rentalContract.getMonthlyRentFee());
        oldRentalContract.setCurrencyCodeOfRentFee(rentalContract.getCurrencyCodeOfRentFee());
        oldRentalContract.setRentalPaymentDay(rentalContract.getRentalPaymentDay());
        oldRentalContract.setStartDate(rentalContract.getStartDate());
        oldRentalContract.setEndDate(rentalContract.getEndDate());

        statusChangeReporter.getCreateRentalContractReporter().submit(
                StatusChangeReportModel.<RentalContract>builder()
                        .type(StatusChangeReportType.UPDATE)
                        .oldEntity(oldRentalContractCopy)
                        .updatedEntity(oldRentalContract)
                        .build());

        rentalContractPersistencePort.save(oldRentalContract);

        return oldRentalContract;
    }

    @Override
    public void delete(Long id) {
        RentalContract rentalContract = findByIdAndIsActive(id, true);

        rentalContract.setIsActive(false);

        rentalContractPersistencePort.save(rentalContract);

        statusChangeReporter.getCreateRentalContractReporter().submit(
                StatusChangeReportModel.<RentalContract>builder()
                        .type(StatusChangeReportType.DELETE)
                        .oldEntity(rentalContract)
                        .build());
    }
}
