package com.padr.gys.infra.inbound.rest.payment.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.payment.port.PaymentDeclarationServicePort;
import com.padr.gys.infra.inbound.rest.payment.model.request.FindPaymentDeclarationRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.PaymentDeclarationResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindPaymentDeclarationWithFilterUseCase {

    private final PaymentDeclarationServicePort paymentDeclarationServicePort;

    public Page<PaymentDeclarationResponse> execute(Pageable pageable, FindPaymentDeclarationRequest request) {
        return paymentDeclarationServicePort
                .findByFilter(pageable, request.getType(), request.getApprovementStatus())
                .map(PaymentDeclarationResponse::of);
    }
}
