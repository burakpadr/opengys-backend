package com.padr.gys.domain.payment.port;

import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;
import com.padr.gys.infra.outbound.persistence.payment.port.PaymentDeclarationPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class PaymentDeclarationServicePortImpl implements PaymentDeclarationServicePort {

    private final PaymentDeclarationPersistencePort paymentDeclarationPersistencePort;

    private final MessageSource messageSource;

    @Override
    public PaymentDeclaration create(PaymentDeclaration paymentDeclaration) {
        return paymentDeclarationPersistencePort.save(paymentDeclaration);
    }

    @Override
    public PaymentDeclaration findById(Long id) {
        return paymentDeclarationPersistencePort.findById(id).orElseThrow(
                () -> new NoSuchElementException(messageSource.getMessage("payment.payment-declaration.not-found", null,
                        LocaleContextHolder.getLocale())));
    }

    @Override
    public Page<PaymentDeclaration> findByFilterAndOwnerId(Pageable pageable, InvoiceType type,
            PaymentDeclarationApprovementStatus approvementStatus, Long ownerId) {

        return paymentDeclarationPersistencePort.findByFilterAndOwnerId(pageable, type, approvementStatus, ownerId);
    }

    @Override
    public Page<PaymentDeclaration> findByFilter(Pageable pageable, InvoiceType type,
            PaymentDeclarationApprovementStatus approvementStatus) {
            
        return paymentDeclarationPersistencePort.findByFilter(pageable, type, approvementStatus);
    }
}
