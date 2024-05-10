package com.padr.gys.domain.payment.port;

import java.util.List;

import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.Invoice;

public interface InvoiceServicePort {

    Invoice save(Invoice invoice);

    List<Invoice> saveAll(List<Invoice> invoices);

    Invoice findById(Long id);

    List<Invoice> findByFilterAsList(InvoiceType type, Long entityId);

    List<Invoice> findMatchableInvoices(InvoiceType type, Long entityId);
}
