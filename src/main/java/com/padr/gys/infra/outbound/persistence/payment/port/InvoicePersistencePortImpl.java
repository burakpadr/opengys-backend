package com.padr.gys.infra.outbound.persistence.payment.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.Invoice;
import com.padr.gys.infra.outbound.persistence.payment.repository.InvoiceRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class InvoicePersistencePortImpl implements InvoicePersistencePort {
    
    private final InvoiceRepository invoiceRepository;

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public Page<Invoice> findByFilterAndRealEstateId(Pageable pageable, InvoiceType type, Long realEstateId) {
        return invoiceRepository.findByFilterAndRealEstateId(pageable, type, realEstateId);
    }

    @Override
    public List<Invoice> saveAll(List<Invoice> invoices) {
        return invoiceRepository.saveAll(invoices);
    }
}
