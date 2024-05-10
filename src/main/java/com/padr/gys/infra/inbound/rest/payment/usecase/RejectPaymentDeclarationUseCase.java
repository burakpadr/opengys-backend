package com.padr.gys.infra.inbound.rest.payment.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;
import com.padr.gys.domain.payment.port.PaymentDeclarationServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RejectPaymentDeclarationUseCase {
    
    private final PaymentDeclarationServicePort paymentDeclarationServicePort;

    public void execute(Long id) {
        PaymentDeclaration paymentDeclaration = paymentDeclarationServicePort.findById(id);

        paymentDeclaration.setApprovementStatus(PaymentDeclarationApprovementStatus.REJECTED);
        paymentDeclaration.setDateOfInvoicePaid(paymentDeclaration.getInvoice().getDateOfInvoice());
        paymentDeclaration.setInvoice(null);

        paymentDeclarationServicePort.save(paymentDeclaration);
    }
}
