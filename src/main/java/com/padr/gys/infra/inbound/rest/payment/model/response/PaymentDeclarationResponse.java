package com.padr.gys.infra.inbound.rest.payment.model.response;

import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;
import com.padr.gys.domain.user.entity.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDeclarationResponse {

    private Long id;
    private InvoiceType type;
    private PaymentDeclarationApprovementStatus approvementStatus;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfInvoicePaid;

    private String receiptRelativeUrl;
    private String declarationOwnerFullName;
    private String realEstateNo;

    public static PaymentDeclarationResponse of(PaymentDeclaration paymentDeclaration) {
        Optional<Archive> receiptOptional = Optional.ofNullable(paymentDeclaration.getReceipt());
        Optional<User> declarationOwnerOptional = Optional.ofNullable(paymentDeclaration.getDeclarationOwner());

        return PaymentDeclarationResponse.builder()
                .id(paymentDeclaration.getId())
                .type(paymentDeclaration.getType())
                .approvementStatus(paymentDeclaration.getApprovementStatus())
                .dateOfInvoicePaid(paymentDeclaration.getDateOfInvoicePaid())
                .receiptRelativeUrl(receiptOptional.isPresent() ? receiptOptional.get().getPath() : null)
                .declarationOwnerFullName(
                        declarationOwnerOptional.isPresent() ? declarationOwnerOptional.get().getFullName() : null)
                .realEstateNo(paymentDeclaration.getRealEstate().getNo())
                .build();
    }
}
