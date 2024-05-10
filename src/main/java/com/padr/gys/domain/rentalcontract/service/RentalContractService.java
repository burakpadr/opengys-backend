package com.padr.gys.domain.rentalcontract.service;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.rentalcontract.port.RentalContractServicePort;
import com.padr.gys.infra.outbound.persistence.rentalcontract.port.RentalContractPersistencePort;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RentalContractService implements RentalContractServicePort {

    private final RentalContractPersistencePort rentalContractPersistencePort;

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
    public RentalContract save(RentalContract rentalContract) {
        return rentalContractPersistencePort.save(rentalContract);
    }

    @Override
    public RentalContract update(RentalContract oldRentalContract, RentalContract newRentalContract) {
        Optional<RentalContract> duplicatedPublishedRentalContractOptional = getDuplicatedPublishedRentalContract(
                oldRentalContract.getRealEstate().getId());

        if (newRentalContract.getIsPublished() && duplicatedPublishedRentalContractOptional.isPresent()) {
            if (!duplicatedPublishedRentalContractOptional.get().getId().equals(oldRentalContract.getId())) {
                throw new EntityExistsException(messageSource.getMessage("rentalcontract.already-exist", null,
                        LocaleContextHolder.getLocale()));
            }
        } else {
            oldRentalContract.setTenant(null);
            oldRentalContract.setIsUpdatable(false);
        }

        oldRentalContract.setMonthlyRentFee(newRentalContract.getMonthlyRentFee());
        oldRentalContract.setCurrencyCodeOfRentFee(newRentalContract.getCurrencyCodeOfRentFee());
        oldRentalContract.setRentalPaymentDay(newRentalContract.getRentalPaymentDay());
        oldRentalContract.setStartDate(newRentalContract.getStartDate());
        oldRentalContract.setEndDate(newRentalContract.getEndDate());
        oldRentalContract.setIsPublished(newRentalContract.getIsPublished());

        if (Objects.nonNull(newRentalContract.getRentalContractFile()))
            oldRentalContract.setRentalContractFile(newRentalContract.getRentalContractFile());

        rentalContractPersistencePort.save(oldRentalContract);

        return oldRentalContract;
    }

    @Override
    public void delete(Long id) {
        RentalContract rentalContract = findById(id);

        rentalContract.setIsDeleted(true);

        rentalContractPersistencePort.save(rentalContract);
    }

    @Override
    public List<RentalContract> findByTenantId(Long tenantId) {
        return rentalContractPersistencePort.findByTenantId(tenantId);
    }

    @Override
    public void deleteAll(List<RentalContract> rentalContracts) {
        rentalContracts.stream().forEach(rentalContract -> {
            rentalContract.setIsDeleted(true);
        });

        rentalContractPersistencePort.saveAll(rentalContracts);
    }

    private Optional<RentalContract> getDuplicatedPublishedRentalContract(Long realEstateId) {
        return rentalContractPersistencePort.findByRealEstateId(realEstateId)
                .stream()
                .filter(RentalContract::getIsPublished)
                .findAny();
    }
}
