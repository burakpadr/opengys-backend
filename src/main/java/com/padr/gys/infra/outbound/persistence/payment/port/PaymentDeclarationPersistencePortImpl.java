package com.padr.gys.infra.outbound.persistence.payment.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;
import com.padr.gys.infra.outbound.persistence.payment.repository.PaymentDeclarationRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class PaymentDeclarationPersistencePortImpl implements PaymentDeclarationPersistencePort {

    private final PaymentDeclarationRepository paymentDeclarationRepository;

    @Override
    public Page<PaymentDeclaration> findByFilterAndOwnerId(Pageable pageable, InvoiceType type,
            PaymentDeclarationApprovementStatus approvementStatus, Long ownerId) {

        return paymentDeclarationRepository.findByFilterAndOwnerId(pageable, type, approvementStatus, ownerId);
    }

    @Override
    public PaymentDeclaration save(PaymentDeclaration paymentDeclaration) {
        return paymentDeclarationRepository.save(paymentDeclaration);
    }

    @Override
    public Optional<PaymentDeclaration> findById(Long id) {
        return paymentDeclarationRepository.findById(id);
    }

    @Override
    public Page<PaymentDeclaration> findByFilter(Pageable pageable, InvoiceType type,
            PaymentDeclarationApprovementStatus approvementStatus) {
        
        return paymentDeclarationRepository.findByFilter(pageable, type, approvementStatus);
    }
}
