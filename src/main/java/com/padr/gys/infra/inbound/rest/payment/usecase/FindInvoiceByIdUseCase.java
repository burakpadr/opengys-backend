package com.padr.gys.infra.inbound.rest.payment.usecase;

import com.padr.gys.domain.payment.entity.Invoice;
import com.padr.gys.infra.outbound.persistence.payment.port.InvoicePersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.payment.model.response.InvoiceResponse;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class FindInvoiceByIdUseCase {
    
    private final InvoicePersistencePort invoicePersistencePort;

    private final MessageSource messageSource;

    public InvoiceResponse execute(Long id) {
        Invoice invoice = invoicePersistencePort.findById(id).orElseThrow(
                () -> new NoSuchElementException(
                        messageSource.getMessage("payment.invoice.not-found", null, LocaleContextHolder.getLocale())));

        return InvoiceResponse.of(invoice);
    }
}
