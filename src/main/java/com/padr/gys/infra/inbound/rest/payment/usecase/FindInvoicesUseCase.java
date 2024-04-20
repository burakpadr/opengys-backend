package com.padr.gys.infra.inbound.rest.payment.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.payment.port.InvoiceServicePort;
import com.padr.gys.infra.inbound.rest.payment.model.request.FindInvoicesRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.InvoiceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindInvoicesUseCase {

    private final InvoiceServicePort invoiceServicePort;

    public Page<InvoiceResponse> execute(Pageable pageable, FindInvoicesRequest request) {
        return invoiceServicePort.findByFilterAndRealEstateId(pageable, request.getType(), request.getRealEstateId())
                .map(InvoiceResponse::of);
    }
}
