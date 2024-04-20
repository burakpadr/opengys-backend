package com.padr.gys.domain.payment.port;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.Invoice;

public interface InvoiceServicePort {

    Invoice create(Invoice invoice);

    List<Invoice> creatAll(List<Invoice> invoices);

    Invoice findById(Long id);

    Page<Invoice> findByFilterAndRealEstateId(Pageable pageable, InvoiceType type, Long realEstateId);
}
