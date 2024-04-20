package com.padr.gys.infra.inbound.rest.payment.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.payment.port.PaymentDeclarationServicePort;
import com.padr.gys.infra.inbound.rest.payment.model.response.PaymentDeclarationResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindPaymentDeclarationByIdUseCase {
    
    private final PaymentDeclarationServicePort paymentDeclarationServicePort;

    public PaymentDeclarationResponse execute(Long id) {
        return PaymentDeclarationResponse.of(paymentDeclarationServicePort.findById(id));
    }
}
