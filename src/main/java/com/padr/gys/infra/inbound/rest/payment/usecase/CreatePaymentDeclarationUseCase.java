package com.padr.gys.infra.inbound.rest.payment.usecase;

import com.padr.gys.infra.outbound.persistence.payment.port.InvoicePersistencePort;
import com.padr.gys.infra.outbound.persistence.payment.port.PaymentDeclarationPersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.TenantPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.common.property.AppProperty;
import com.padr.gys.domain.common.util.ArchiveUtil;
import com.padr.gys.domain.dashboard.constant.EnumRentPaymentStatusStatisticElementType;
import com.padr.gys.domain.dashboard.context.DashboardHandlerContext;
import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic;
import com.padr.gys.domain.payment.entity.Invoice;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;
import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.infra.inbound.common.context.UserContext;
import com.padr.gys.infra.inbound.rest.payment.model.request.CreatePaymentDeclarationRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.PaymentDeclarationResponse;
import com.padr.gys.infra.outbound.persistence.archive.port.ArchivePersistencePort;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class CreatePaymentDeclarationUseCase {

    private final ArchivePersistencePort archivePersistencePort;
    private final PaymentDeclarationPersistencePort paymentDeclarationPersistencePort;
    private final InvoicePersistencePort invoicePersistencePort;
    private final TenantPersistencePort tenantPersistencePort;

    private final AppProperty appProperty;
    private final ArchiveUtil archiveUtil;
    private final MessageSource messageSource;

    public PaymentDeclarationResponse execute(MultipartFile receipt, CreatePaymentDeclarationRequest request) {
        Tenant declarationOwnerTenant = tenantPersistencePort.findByUserId(UserContext.getUser().getId())
                .orElseThrow(() -> new NoSuchElementException(
                messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

        // Upload receipt file

        Archive receiptArchive = archiveUtil.prepareArchive(receipt, declarationOwnerTenant.getId(),
                appProperty.getStorage().getRentalContractFilesPath(),
                appProperty.getStorage().getRentalContractFilesRelativeUrl());

        archivePersistencePort.save(receiptArchive);

        // Create payment declaration

        Invoice invoice = invoicePersistencePort.findById(request.getInvoiceId()).orElseThrow(
                () -> new NoSuchElementException(
                        messageSource.getMessage("payment.invoice.not-found", null, LocaleContextHolder.getLocale())));

        PaymentDeclaration paymentDeclaration = switch (request.getInvoiceType()) {
            case RENT_PAYMENT -> {
                Long entityId = declarationOwnerTenant.getRentalContract().getId();

                PaymentDeclaration p = request.to(receiptArchive, declarationOwnerTenant, invoice,
                        entityId);

                yield paymentDeclarationPersistencePort.save(p);
            }
            default -> throw new IllegalArgumentException();
        };

        DashboardHandlerContext.getDashboardHandler(RentPaymentStatusStatistic.class)
                .updateStatisticElement(EnumRentPaymentStatusStatisticElementType.PENDING);

        return PaymentDeclarationResponse.of(paymentDeclaration);
    }
}
