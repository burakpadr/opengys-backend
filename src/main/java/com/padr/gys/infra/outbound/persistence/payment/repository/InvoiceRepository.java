package com.padr.gys.infra.outbound.persistence.payment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                i.realEstate.id = :realEstateId
            """)
    Page<Invoice> findByFilterAndRealEstateId(Pageable pageable, InvoiceType type, Long realEstateId);
}
