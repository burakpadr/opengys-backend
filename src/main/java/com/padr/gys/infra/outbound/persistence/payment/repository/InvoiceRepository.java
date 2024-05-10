package com.padr.gys.infra.outbound.persistence.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("""
            SELECT i FROM Invoice i
            WHERE
                (:type IS NULL OR i.type = :type) AND
                (:entityId IS NULL OR i.entityId = :entityId)
                ORDER BY i.dateOfInvoice ASC
            """)
    List<Invoice> findByFilterAsList(InvoiceType type, Long entityId);

    @Query("""
        SELECT i FROM Invoice i
        WHERE
            (:type IS NULL OR i.type = :type) AND
            (:entityId IS NULL OR i.entityId = :entityId) AND
            i.paymentDeclaration IS NULL
            ORDER BY i.dateOfInvoice ASC
        """)
    List<Invoice> findMatchableInvoices(InvoiceType type, Long entityId);
}
