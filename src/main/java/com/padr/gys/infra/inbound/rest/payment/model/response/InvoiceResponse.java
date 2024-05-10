package com.padr.gys.infra.inbound.rest.payment.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.entity.Invoice;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoiceResponse {

    private Long id;
    private InvoiceType type;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfInvoicePaid;

    private Boolean isPaid;
    private String receiptRelativeUrl;
    private String currencyCode;
    private BigDecimal amount;

    public static InvoiceResponse of(Invoice invoice) {
        Optional<PaymentDeclaration> paymentDeclarationOptional = Optional.ofNullable(invoice.getPaymentDeclaration());

        Boolean isPaid = false;
        String receiptRelativeUrl = null;

        if (paymentDeclarationOptional.isPresent()) {
            isPaid = paymentDeclarationOptional.get().getApprovementStatus().equals(PaymentDeclarationApprovementStatus.APPROVED);
            
            if (isPaid) {
                receiptRelativeUrl = paymentDeclarationOptional.get().getReceipt().getPath();
            }
        }

        return InvoiceResponse.builder()
                .id(invoice.getId())
                .type(invoice.getType())
                .isPaid(isPaid)
                .dateOfInvoicePaid(invoice.getDateOfInvoice())
                .receiptRelativeUrl(receiptRelativeUrl)
                .currencyCode(invoice.getCurrencyCode())
                .amount(invoice.getAmount())
                .build();
    }
}
