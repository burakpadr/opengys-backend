package com.padr.gys.domain.payment.port;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.payment.constant.InvoiceType;
import com.padr.gys.domain.payment.entity.Invoice;
import com.padr.gys.infra.outbound.persistence.payment.port.InvoicePersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class InvoiceServicePortImpl implements InvoiceServicePort {

    private final InvoicePersistencePort invoicePersistencePort;

    private final MessageSource messageSource;

    @Override
    public Invoice create(Invoice invoice) {
        return invoicePersistencePort.save(invoice);
    }

    @Override
    public Invoice findById(Long id) {
        return invoicePersistencePort.findById(id).orElseThrow(
                () -> new NoSuchElementException(
                        messageSource.getMessage("payment.invoice.not-found", null, LocaleContextHolder.getLocale())));
    }

    @Override
    public Page<Invoice> findByFilterAndRealEstateId(Pageable pageable, InvoiceType type, Long realEstateId) {
        return invoicePersistencePort.findByFilterAndRealEstateId(pageable, type, realEstateId);
    }

    @Override
    public List<Invoice> creatAll(List<Invoice> invoices) {
        return invoicePersistencePort.saveAll(invoices);
    }
}
