package com.padr.gys.infra.outbound.persistence.payment.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;

public interface PaymentDeclarationPersistencePort {

    Page<PaymentDeclaration> findByFilter(Pageable pageable, InvoiceType type,
            PaymentDeclarationApprovementStatus approvementStatus, Long ownerId);

    PaymentDeclaration save(PaymentDeclaration paymentDeclaration);

    Optional<PaymentDeclaration> findById(Long id);
}
