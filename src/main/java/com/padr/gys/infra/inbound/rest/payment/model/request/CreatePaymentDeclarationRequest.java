package com.padr.gys.infra.inbound.rest.payment.model.request;

import java.time.LocalDate;

import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.user.entity.User;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentDeclarationRequest {
    
    @NotNull
    private InvoiceType type;

    @NotNull
    private LocalDate dateOfInvoicePaid;

    public PaymentDeclaration to(Archive receipt, User declarationOwner, RealEstate realEstate) {
        return PaymentDeclaration.builder()
                .type(type)
                .approvementStatus(PaymentDeclarationApprovementStatus.WAITING)
                .dateOfInvoicePaid(dateOfInvoicePaid)
                .receipt(receipt)
                .declarationOwner(declarationOwner)
                .realEstate(realEstate)
                .build();
    }
}
