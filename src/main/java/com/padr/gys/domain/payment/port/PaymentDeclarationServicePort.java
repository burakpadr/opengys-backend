package com.padr.gys.domain.payment.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;

public interface PaymentDeclarationServicePort {

    PaymentDeclaration create(PaymentDeclaration paymentDeclaration);

    PaymentDeclaration findById(Long id);

    Page<PaymentDeclaration> findByFilterAndOwnerId(Pageable pageable, InvoiceType type,
            PaymentDeclarationApprovementStatus approvementStatus, Long ownerId);

    Page<PaymentDeclaration> findByFilter(Pageable pageable, InvoiceType type,
            PaymentDeclarationApprovementStatus approvementStatus);
}
