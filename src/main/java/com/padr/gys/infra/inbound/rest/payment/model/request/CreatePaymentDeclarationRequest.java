package com.padr.gys.infra.inbound.rest.payment.model.request;

import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.Invoice;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;
import com.padr.gys.domain.user.entity.Tenant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentDeclarationRequest {

    @NotNull
    private InvoiceType invoiceType;

    @NotNull
    @Positive
    private Long invoiceId;

    public PaymentDeclaration to(Archive receipt, Tenant declarationOwnerTenant, Invoice invoice, Long entityId) {
        return PaymentDeclaration.builder()
                .type(invoiceType)
                .approvementStatus(PaymentDeclarationApprovementStatus.WAITING)
                .invoice(invoice)
                .receipt(receipt)
                .declarationOwner(declarationOwnerTenant)
                .entityId(entityId)
                .build();
    }
}
