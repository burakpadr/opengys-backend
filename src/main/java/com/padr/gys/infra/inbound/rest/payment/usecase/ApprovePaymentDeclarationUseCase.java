package com.padr.gys.infra.inbound.rest.payment.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;
import com.padr.gys.domain.payment.port.PaymentDeclarationServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApprovePaymentDeclarationUseCase {
    
    private final PaymentDeclarationServicePort paymentDeclarationServicePort;

    public void execute(Long paymentDeclarationId) {
        PaymentDeclaration paymentDeclaration = paymentDeclarationServicePort.findById(paymentDeclarationId);

        paymentDeclaration.setApprovementStatus(PaymentDeclarationApprovementStatus.APPROVED);
        paymentDeclarationServicePort.save(paymentDeclaration);
    }
}
