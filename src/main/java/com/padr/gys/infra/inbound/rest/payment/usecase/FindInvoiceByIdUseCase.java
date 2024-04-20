package com.padr.gys.infra.inbound.rest.payment.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.payment.port.InvoiceServicePort;
import com.padr.gys.infra.inbound.rest.payment.model.response.InvoiceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindInvoiceByIdUseCase {
    
    private final InvoiceServicePort invoiceServicePort;

    public InvoiceResponse execute(Long id) {
        return InvoiceResponse.of(invoiceServicePort.findById(id));
    }
}
