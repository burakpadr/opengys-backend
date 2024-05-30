package com.padr.gys.infra.inbound.rest.rentalcontract.usecase;

import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.common.property.AppProperty;
import com.padr.gys.domain.common.util.ArchiveUtil;
import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.Invoice;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.infra.inbound.rest.rentalcontract.model.request.UpdateRentalContractRequest;
import com.padr.gys.infra.inbound.rest.rentalcontract.model.response.RentalContractResponse;
import com.padr.gys.infra.outbound.persistence.archive.port.ArchivePersistencePort;
import com.padr.gys.infra.outbound.persistence.payment.port.InvoicePersistencePort;

import com.padr.gys.infra.outbound.persistence.rentalcontract.port.RentalContractPersistencePort;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UpdateRentalContractUseCase {

    private final RentalContractPersistencePort rentalContractPersistencePort;
    private final ArchivePersistencePort archivePersistencePort;
    private final InvoicePersistencePort invoicePersistencePort;

    private final AppProperty appProperty;
    private final ArchiveUtil archiveUtil;
    private final MessageSource messageSource;

    public RentalContractResponse execute(Long id, Optional<MultipartFile> rentalContractFile,
            UpdateRentalContractRequest request) {

        RentalContract oldRentalContract = rentalContractPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("rentalcontract.not-found", null, LocaleContextHolder.getLocale())));

        RentalContract newRentalContract = request.to();

        if (rentalContractFile.isPresent()) {
            // Upload rental contract file

            Archive rentalContractFileArchive = archiveUtil.prepareArchive(rentalContractFile.get(),
                    oldRentalContract.getRealEstate().getId(),
                    appProperty.getStorage().getRentalContractFilesPath(),
                    appProperty.getStorage().getRentalContractFilesRelativeUrl());

            archivePersistencePort.save(rentalContractFileArchive);

            newRentalContract.setRentalContractFile(rentalContractFileArchive);
        }

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

        if (oldRentalContract.getIsPublished()) {
            List<Invoice> invoices = invoicePersistencePort.findByFilterAsList(InvoiceType.RENT_PAYMENT,
                    oldRentalContract.getId());

            invoices.forEach(invoice -> {
                invoice.setIsPublished(false);
            });

            invoicePersistencePort.saveAll(invoices);
        }

        return RentalContractResponse.of(oldRentalContract);
    }

    private Optional<RentalContract> getDuplicatedPublishedRentalContract(Long realEstateId) {
        return rentalContractPersistencePort.findByRealEstateId(realEstateId)
                .stream()
                .filter(RentalContract::getIsPublished)
                .findAny();
    }
}
