package com.padr.gys.infra.inbound.rest.payment.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.payment.port.InvoiceServicePort;
import com.padr.gys.infra.inbound.rest.payment.model.request.FindInvoicesByFilterRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.InvoiceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindInvoicesByFilterAsList {

    private final InvoiceServicePort invoiceServicePort;

    public List<InvoiceResponse> execute(FindInvoicesByFilterRequest request) {
        return invoiceServicePort
                .findByFilterAsList(request.getType(), request.getEntityId())
                .stream()
                .map(InvoiceResponse::of)
                .toList();
    }
}
