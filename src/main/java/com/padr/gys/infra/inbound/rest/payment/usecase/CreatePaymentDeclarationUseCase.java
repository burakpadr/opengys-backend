package com.padr.gys.infra.inbound.rest.payment.usecase;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.archive.port.ArchiveServicePort;
import com.padr.gys.domain.common.property.AppProperty;
import com.padr.gys.domain.payment.entity.Invoice;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;
import com.padr.gys.domain.payment.port.InvoiceServicePort;
import com.padr.gys.domain.payment.port.PaymentDeclarationServicePort;
import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.domain.user.port.TenantServicePort;
import com.padr.gys.infra.inbound.common.context.UserContext;
import com.padr.gys.infra.inbound.rest.payment.model.request.CreatePaymentDeclarationRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.PaymentDeclarationResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreatePaymentDeclarationUseCase {

    private final PaymentDeclarationServicePort paymentDeclarationServicePort;
    private final ArchiveServicePort archiveServicePort;
    private final TenantServicePort tenantServicePort;
    private final InvoiceServicePort invoiceServicePort;

    private final AppProperty appProperty;

    public PaymentDeclarationResponse execute(MultipartFile receipt, CreatePaymentDeclarationRequest request) {
        Tenant declarationOwnerTenant = tenantServicePort.findByUserId(UserContext.getUser().getId()); 

        // Upload receipt file

        Archive receiptArchive = archiveServicePort.create(receipt, declarationOwnerTenant.getId(),
                appProperty.getStorage().getRentalContractFilesPath(),
                appProperty.getStorage().getRentalContractFilesRelativeUrl());

        // Create payment declaration

        Invoice invoice = invoiceServicePort.findById(request.getInvoiceId());

        PaymentDeclaration paymentDeclaration = switch (request.getInvoiceType()) {
            case RENT_PAYMENT -> {
                Long entityId = declarationOwnerTenant.getRentalContract().getId();

                PaymentDeclaration p = request.to(receiptArchive, declarationOwnerTenant, invoice,
                        entityId);

                yield paymentDeclarationServicePort.save(p);
            }
            default -> throw new IllegalArgumentException();
        };

        return PaymentDeclarationResponse.of(paymentDeclaration);
    }
}
