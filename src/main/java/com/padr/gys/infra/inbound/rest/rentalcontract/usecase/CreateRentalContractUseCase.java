package com.padr.gys.infra.inbound.rest.rentalcontract.usecase;

import com.padr.gys.common.util.DateUtil;
import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.common.property.AppProperty;
import com.padr.gys.domain.common.util.ArchiveUtil;
import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.Invoice;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.infra.inbound.rest.rentalcontract.model.request.CreateRentalContractRequest;
import com.padr.gys.infra.inbound.rest.rentalcontract.model.response.RentalContractResponse;
import com.padr.gys.infra.outbound.persistence.archive.port.ArchivePersistencePort;
import com.padr.gys.infra.outbound.persistence.payment.port.InvoicePersistencePort;

import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import com.padr.gys.infra.outbound.persistence.rentalcontract.port.RentalContractPersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.TenantPersistencePort;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class CreateRentalContractUseCase {

    private final RentalContractPersistencePort rentalContractPersistencePort;
    private final ArchivePersistencePort archivePersistencePort;
    private final InvoicePersistencePort invoicePersistencePort;
    private final RealEstatePersistencePort realEstatePersistencePort;
    private final TenantPersistencePort tenantPersistencePort;

    private final MessageSource messageSource;
    private final AppProperty appProperty;
    private final ArchiveUtil archiveUtil;

    public RentalContractResponse execute(Optional<MultipartFile> rentalContractFile,
            CreateRentalContractRequest request) {

        throwExceptionIfRentalContractAlreadyPusblished(request.getRealEstateId());

        RentalContract rentalContract = request.to();

        RealEstate realEstate = realEstatePersistencePort.findById(request.getRealEstateId())
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("realestate.not-found", null, LocaleContextHolder.getLocale())));

        Tenant tenant = tenantPersistencePort.findById(request.getTenantId()).orElseThrow(() -> new NoSuchElementException(
                messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

        // Upload rental contract file

        if (rentalContractFile.isPresent()) {
            Archive rentalContractFileArchive = archiveUtil.prepareArchive(rentalContractFile.get(), realEstate.getId(),
                    appProperty.getStorage().getRentalContractFilesPath(),
                    appProperty.getStorage().getRentalContractFilesRelativeUrl());

            archivePersistencePort.save(rentalContractFileArchive);

            rentalContract.setRentalContractFile(rentalContractFileArchive);
        }

        // Create rental contract

        rentalContract.setRealEstate(realEstate);
        rentalContract.setTenant(tenant);
        rentalContractPersistencePort.save(rentalContract);

        // Create rent invoices

        invoicePersistencePort.saveAll(prepareRentInvoices(rentalContract));

        return RentalContractResponse.of(rentalContract);
    }

    private void throwExceptionIfRentalContractAlreadyPusblished(Long realEstateId) {
        if (!rentalContractPersistencePort.findByRealEstateIdAndIsPublished(realEstateId, true).isEmpty()) {
            throw new EntityExistsException(messageSource.getMessage("rentalcontract.already-exist", null,
                    LocaleContextHolder.getLocale()));
        }
    }

    private List<Invoice> prepareRentInvoices(RentalContract rentalContract) {
        Long rentalContractValidityPeriodAsMonths = DateUtil.differenceBetween(ChronoUnit.MONTHS,
                rentalContract.getStartDate(), rentalContract.getEndDate()) + 1;

        return LongStream.range(0, rentalContractValidityPeriodAsMonths).boxed().map(index -> {
            int invoiceYear = rentalContract.getStartDate().plusMonths(index).getYear();
            int invoiceMonth = rentalContract.getStartDate().plusMonths(index).getMonthValue();

            YearMonth yearMonth = YearMonth.of(invoiceYear, invoiceMonth);
            int daysInMonth = yearMonth.lengthOfMonth();

            LocalDate dateOfInvoice;
            BigDecimal invoiceFeePaid = BigDecimal.ZERO;

            if (index == 0) {
                long numberOfDaysInvoicePaid;

                int rentalContractStartDay = rentalContract.getStartDate().getDayOfMonth();
                int rentalContractEndDay = rentalContract.getEndDate().getDayOfMonth();

                if (rentalContractValidityPeriodAsMonths == 1) {
                    if (rentalContract.getRentalPaymentDay() >= rentalContractStartDay
                            && rentalContract.getRentalPaymentDay() <= rentalContractEndDay) {
                        dateOfInvoice = LocalDate.of(invoiceYear, invoiceMonth, rentalContract.getRentalPaymentDay());
                    } else {
                        dateOfInvoice = rentalContract.getStartDate();
                    }

                    numberOfDaysInvoicePaid = DateUtil.differenceBetween(ChronoUnit.DAYS,
                            rentalContract.getStartDate(), rentalContract.getEndDate()) + 1;

                    invoiceFeePaid = rentalContract.getMonthlyRentFee();
                } else {
                    dateOfInvoice = rentalContract.getStartDate()
                            .getDayOfMonth() > rentalContract
                                    .getRentalPaymentDay().intValue()
                                            ? rentalContract.getStartDate()
                                            : LocalDate.of(invoiceYear,
                                                    invoiceMonth,
                                                    rentalContract.getRentalPaymentDay());

                    numberOfDaysInvoicePaid = daysInMonth - rentalContract.getStartDate().getDayOfMonth() + 1;

                    invoiceFeePaid = rentalContract.getMonthlyRentFee()
                            .divide(new BigDecimal(daysInMonth), 2, RoundingMode.HALF_UP)
                            .multiply(new BigDecimal(numberOfDaysInvoicePaid));
                }

            } else if (index == rentalContractValidityPeriodAsMonths - 1) {
                dateOfInvoice = rentalContract.getEndDate()
                        .getDayOfMonth() > rentalContract
                                .getRentalPaymentDay().intValue() ? LocalDate.of(invoiceYear, invoiceMonth,
                                        rentalContract.getRentalPaymentDay())
                                        : rentalContract.getEndDate();

                invoiceFeePaid = rentalContract.getMonthlyRentFee()
                        .divide(new BigDecimal(daysInMonth), 2, RoundingMode.HALF_UP)
                        .multiply(new BigDecimal(dateOfInvoice.getDayOfMonth()));
            } else {
                invoiceFeePaid = rentalContract.getMonthlyRentFee();
                dateOfInvoice = LocalDate.of(invoiceYear, invoiceMonth, rentalContract.getRentalPaymentDay());
            }

            return Invoice.builder()
                    .type(InvoiceType.RENT_PAYMENT)
                    .dateOfInvoice(dateOfInvoice)
                    .currencyCode(rentalContract.getCurrencyCodeOfRentFee())
                    .amount(invoiceFeePaid)
                    .entityId(rentalContract.getId())
                    .realEstate(rentalContract.getRealEstate())
                    .build();
        }).collect(Collectors.toList());
    }
}
