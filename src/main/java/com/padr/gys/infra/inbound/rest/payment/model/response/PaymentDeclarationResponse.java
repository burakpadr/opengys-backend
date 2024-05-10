package com.padr.gys.infra.inbound.rest.payment.model.response;

import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.Invoice;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;
import com.padr.gys.domain.user.entity.Tenant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDeclarationResponse {

    private Long id;
    private String realEstateNo;
    private InvoiceType invoiceTypeAlias;
    private String invoiceTypeValue;
    private PaymentDeclarationApprovementStatus approvementStatusAlias;
    private String approvementStatusValue;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfInvoicePaid;

    private String receiptRelativeUrl;
    private String declarationOwnerFullName;

    public static PaymentDeclarationResponse of(PaymentDeclaration paymentDeclaration) {
        Optional<Archive> receiptOptional = Optional.ofNullable(paymentDeclaration.getReceipt());
        Optional<Tenant> declarationOwnerOptional = Optional.ofNullable(paymentDeclaration.getDeclarationOwner());
        Optional<Invoice> invoiceOptional = Optional.ofNullable(paymentDeclaration.getInvoice());

        String realEstateNo = "";
        String declarationOwnerFullName = "";
        LocalDate dateOfInvoicePaid = null;

        if (declarationOwnerOptional.isPresent()) {
            realEstateNo = declarationOwnerOptional.get().getRentalContract().getRealEstate().getNo();
            declarationOwnerFullName = declarationOwnerOptional.get().getUser().getFullName();
        }

        if (invoiceOptional.isPresent()) {
            dateOfInvoicePaid = invoiceOptional.get().getDateOfInvoice();
        } else {
            dateOfInvoicePaid = paymentDeclaration.getDateOfInvoicePaid();
        }

        return PaymentDeclarationResponse.builder()
                .id(paymentDeclaration.getId())
                .realEstateNo(realEstateNo)
                .invoiceTypeAlias(paymentDeclaration.getType())
                .invoiceTypeValue(paymentDeclaration.getType().getValue())
                .approvementStatusAlias(paymentDeclaration.getApprovementStatus())
                .approvementStatusValue(paymentDeclaration.getApprovementStatus().getValue())
                .dateOfInvoicePaid(dateOfInvoicePaid)
                .receiptRelativeUrl(receiptOptional.isPresent() ? receiptOptional.get().getPath() : null)
                .declarationOwnerFullName(declarationOwnerFullName)
                .build();
    }
}
