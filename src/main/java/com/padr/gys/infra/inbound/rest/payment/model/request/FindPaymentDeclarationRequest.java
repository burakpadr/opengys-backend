package com.padr.gys.infra.inbound.rest.payment.model.request;

import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.constant.InvoiceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPaymentDeclarationRequest {

    private InvoiceType invoiceType;
    private PaymentDeclarationApprovementStatus approvementStatus;
}
