package com.padr.gys.infra.inbound.rest.payment.usecase;

import com.padr.gys.domain.payment.entity.PaymentDeclaration;
import com.padr.gys.infra.outbound.persistence.payment.port.PaymentDeclarationPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.payment.model.response.PaymentDeclarationResponse;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class FindPaymentDeclarationByIdUseCase {
    
    private final PaymentDeclarationPersistencePort paymentDeclarationPersistencePort;

    private final MessageSource messageSource;

    public PaymentDeclarationResponse execute(Long id) {
        PaymentDeclaration paymentDeclaration = paymentDeclarationPersistencePort.findById(id).orElseThrow(
                () -> new NoSuchElementException(messageSource.getMessage("payment.payment-declaration.not-found", null,
                        LocaleContextHolder.getLocale())));

        return PaymentDeclarationResponse.of(paymentDeclaration);
    }
}
