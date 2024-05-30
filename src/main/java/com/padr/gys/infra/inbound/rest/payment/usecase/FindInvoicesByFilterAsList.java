package com.padr.gys.infra.inbound.rest.payment.usecase;

import java.util.List;

import com.padr.gys.infra.outbound.persistence.payment.port.InvoicePersistencePort;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.payment.model.request.FindInvoicesByFilterRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.InvoiceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindInvoicesByFilterAsList {

    private final InvoicePersistencePort invoicePersistencePort;

    public List<InvoiceResponse> execute(FindInvoicesByFilterRequest request) {
        return invoicePersistencePort
                .findByFilterAsList(request.getType(), request.getEntityId())
                .stream()
                .map(InvoiceResponse::of)
                .toList();
    }
}
