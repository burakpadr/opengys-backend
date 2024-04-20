package com.padr.gys.infra.inbound.rest.payment.usecase;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.archive.port.ArchiveServicePort;
import com.padr.gys.domain.common.property.AppProperty;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;
import com.padr.gys.domain.payment.port.PaymentDeclarationServicePort;
import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.domain.user.entity.User;
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

    private final AppProperty appProperty;

    public PaymentDeclarationResponse execute(MultipartFile receipt, CreatePaymentDeclarationRequest request) {
        User declarationOwnerUser = UserContext.getUser();
        Tenant declarationOwnerTenant = tenantServicePort.findByUserId(declarationOwnerUser.getId());

        // Upload receipt file

        Archive receiptArchive = archiveServicePort.create(receipt, declarationOwnerUser.getId(),
                appProperty.getStorage().getRentalContractFilesPath(),
                appProperty.getStorage().getRentalContractFilesRelativeUrl());

        // Create payment declaration

        PaymentDeclaration paymentDeclaration = request.to(receiptArchive, declarationOwnerUser,
                declarationOwnerTenant.getRentalContract().getRealEstate());

        paymentDeclarationServicePort.create(paymentDeclaration);

        return PaymentDeclarationResponse.of(paymentDeclaration);
    }
}
