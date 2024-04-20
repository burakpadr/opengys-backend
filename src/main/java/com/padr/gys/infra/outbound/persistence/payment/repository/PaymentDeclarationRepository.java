package com.padr.gys.infra.outbound.persistence.payment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.payment.constant.PaymentDeclarationApprovementStatus;
import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.PaymentDeclaration;

@Repository
public interface PaymentDeclarationRepository extends JpaRepository<PaymentDeclaration, Long> {

    @Query("""
            SELECT pd FROM PaymentDeclaration pd
            WHERE
                (:type IS NULL OR pd.type = :type) AND
                (:approvementStatus IS NULL or pd.approvementStatus =: approvementStatus) AND
                pd.declarationOwner.id = :ownerId
            """)
    Page<PaymentDeclaration> findByFilterAndOwnerId(Pageable pageable, InvoiceType type,
            PaymentDeclarationApprovementStatus approvementStatus, Long ownerId);

    @Query("""
            SELECT pd FROM PaymentDeclaration pd
            WHERE
                (:type IS NULL OR pd.type = :type) AND
                (:approvementStatus IS NULL or pd.approvementStatus =: approvementStatus)
            """)
    Page<PaymentDeclaration> findByFilter(Pageable pageable, InvoiceType type,
            PaymentDeclarationApprovementStatus approvementStatus);
}
