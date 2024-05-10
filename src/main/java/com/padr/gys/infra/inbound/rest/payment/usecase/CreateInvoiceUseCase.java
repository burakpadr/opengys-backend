package com.padr.gys.infra.inbound.rest.payment.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.payment.entity.Invoice;
import com.padr.gys.domain.payment.port.InvoiceServicePort;
import com.padr.gys.infra.inbound.rest.payment.model.request.CreateInvoiceRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.InvoiceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateInvoiceUseCase {
    
    private final InvoiceServicePort invoiceServicePort;

    public InvoiceResponse execute(CreateInvoiceRequest request) {
        Invoice invoice = request.to();

        return InvoiceResponse.of(invoiceServicePort.save(invoice));
    }
}
