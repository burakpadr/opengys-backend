package com.padr.gys.infra.inbound.rest.payment.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.payment.entity.Invoice;
import com.padr.gys.domain.payment.port.InvoiceServicePort;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;
import com.padr.gys.infra.inbound.rest.payment.model.request.CreateInvoiceRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.InvoiceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateInvoiceUseCase {
    
    private final InvoiceServicePort invoiceServicePort;
    private final RealEstateServicePort realEstateServicePort;

    public InvoiceResponse execute(CreateInvoiceRequest request) {
        RealEstate realEstate = realEstateServicePort.findById(request.getRealEstateId());
        Invoice invoice = request.to(realEstate);

        return InvoiceResponse.of(invoiceServicePort.create(invoice));
    }
}
