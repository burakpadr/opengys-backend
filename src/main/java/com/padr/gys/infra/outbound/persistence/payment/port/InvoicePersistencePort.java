package com.padr.gys.infra.outbound.persistence.payment.port;

import java.util.List;
import java.util.Optional;

import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.Invoice;

public interface InvoicePersistencePort {

    Invoice save(Invoice invoice);

    List<Invoice> saveAll(List<Invoice> invoices);

    Optional<Invoice> findById(Long id);

    List<Invoice> findByFilterAsList(InvoiceType type, Long entityId);

    List<Invoice> findMatchableInvoices(InvoiceType type, Long entityId);
}
