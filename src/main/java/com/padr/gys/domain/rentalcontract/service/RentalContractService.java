package com.padr.gys.domain.rentalcontract.service;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.rentalcontract.port.RentalContractServicePort;
import com.padr.gys.domain.statusmanager.constant.StatusChangeOperationType;
import com.padr.gys.domain.statusmanager.context.StatusChangeHandlerContext;
import com.padr.gys.domain.statusmanager.model.StatusChangeModel;
import com.padr.gys.infra.outbound.persistence.rentalcontract.port.RentalContractPersistencePort;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RentalContractService implements RentalContractServicePort {

    private final RentalContractPersistencePort rentalContractPersistencePort;

    private final StatusChangeHandlerContext statusChangeHandlerContext;

    private final MessageSource messageSource;

    @Override
    public Page<RentalContract> findByRealEstateId(Long realEstateId, Pageable pageable) {
        return rentalContractPersistencePort.findByRealEstateId(realEstateId, pageable);
    }

    @Override
    public List<RentalContract> findByRealEstateIdAndIsPublished(Long realEstateId, Boolean isPublished) {
        return rentalContractPersistencePort.findByRealEstateIdAndIsPublished(realEstateId, isPublished);
    }

    @Override
    public RentalContract findById(Long id) {
        return rentalContractPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("rentalcontract.not-found", null, LocaleContextHolder.getLocale())));
    }

    @Override
    public RentalContract create(RentalContract rentalContract) {
        throwExceptionIfRentalContractAlreadyPusblished(rentalContract.getRealEstate().getId());

        rentalContractPersistencePort.save(rentalContract);

        StatusChangeModel model = StatusChangeModel.builder()
                .type(StatusChangeOperationType.CREATE)
                .oldEntity(rentalContract)
                .build();

        statusChangeHandlerContext.getStatusChangeHandler(RentalContract.class).handle(model);

        return rentalContract;
    }

    @Override
    public RentalContract update(Long id, RentalContract rentalContract) {
        RentalContract oldRentalContract = findById(id);

        throwExceptionIfRentalContractAlreadyPusblished(oldRentalContract.getRealEstate().getId());

        // RentalContract oldRentalContractCopy = new RentalContract(oldRentalContract);

        oldRentalContract.setMonthlyRentFee(rentalContract.getMonthlyRentFee());
        oldRentalContract.setCurrencyCodeOfRentFee(rentalContract.getCurrencyCodeOfRentFee());
        oldRentalContract.setRentalPaymentDay(rentalContract.getRentalPaymentDay());
        oldRentalContract.setStartDate(rentalContract.getStartDate());
        oldRentalContract.setEndDate(rentalContract.getEndDate());
        oldRentalContract.setIsPublished(rentalContract.getIsPublished());

        // StatusChangeModel model = StatusChangeModel.builder()
        // .type(StatusChangeOperationType.UPDATE)
        // .oldEntity(oldRentalContractCopy)
        // .updatedEntity(oldRentalContract)
        // .build();

        // statusChangeHandlerContext.getStatusChangeHandler(RentalContract.class).handle(model);

        rentalContractPersistencePort.save(oldRentalContract);

        return oldRentalContract;
    }

    @Override
    public void delete(Long id) {
        RentalContract rentalContract = findById(id);

        rentalContract.setIsDeleted(true);

        rentalContractPersistencePort.save(rentalContract);

        StatusChangeModel model = StatusChangeModel.builder()
                .type(StatusChangeOperationType.DELETE)
                .oldEntity(rentalContract)
                .build();

        statusChangeHandlerContext.getStatusChangeHandler(RentalContract.class).handle(model);
    }

    private void throwExceptionIfRentalContractAlreadyPusblished(Long realEstateId) {
        rentalContractPersistencePort.findByRealEstateId(realEstateId)
                .stream()
                .filter(RentalContract::getIsPublished)
                .findAny()
                .ifPresent(rc -> {
                    throw new EntityExistsException(messageSource.getMessage("rentalcontract.already-exist", null,
                            LocaleContextHolder.getLocale()));
                });
    }
}
