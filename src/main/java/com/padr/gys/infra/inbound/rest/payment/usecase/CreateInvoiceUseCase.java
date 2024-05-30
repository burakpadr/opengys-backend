package com.padr.gys.infra.inbound.rest.payment.usecase;

import com.padr.gys.infra.outbound.persistence.payment.port.InvoicePersistencePort;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.payment.entity.Invoice;
import com.padr.gys.infra.inbound.rest.payment.model.request.CreateInvoiceRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.InvoiceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateInvoiceUseCase {
    
    private final InvoicePersistencePort invoicePersistencePort;

    public InvoiceResponse execute(CreateInvoiceRequest request) {
        Invoice invoice = request.to();

        return InvoiceResponse.of(invoicePersistencePort.save(invoice));
    }
}
