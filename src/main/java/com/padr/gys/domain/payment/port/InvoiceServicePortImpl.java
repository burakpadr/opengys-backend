package com.padr.gys.domain.payment.port;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
    public Invoice save(Invoice invoice) {
        return invoicePersistencePort.save(invoice);
    }

    @Override
    public Invoice findById(Long id) {
        return invoicePersistencePort.findById(id).orElseThrow(
                () -> new NoSuchElementException(
                        messageSource.getMessage("payment.invoice.not-found", null, LocaleContextHolder.getLocale())));
    }

    @Override
    public List<Invoice> saveAll(List<Invoice> invoices) {
        return invoicePersistencePort.saveAll(invoices);
    }

    @Override
    public List<Invoice> findByFilterAsList(InvoiceType type, Long entityId) {
        return invoicePersistencePort.findByFilterAsList(type, entityId);
    }

    @Override
    public List<Invoice> findMatchableInvoices(InvoiceType type, Long entityId) {
        return  invoicePersistencePort.findMatchableInvoices(type, entityId);
    }
}
