package com.padr.gys.infra.inbound.rest.payment.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.payment.port.InvoiceServicePort;
import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.domain.user.port.TenantServicePort;
import com.padr.gys.infra.inbound.common.context.UserContext;
import com.padr.gys.infra.inbound.rest.payment.model.request.FindInvoicesByFilterRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.InvoiceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindMatchableInvoicesUseCase {

    private final InvoiceServicePort invoiceServicePort;
    private final TenantServicePort tenantServicePort;

    public List<InvoiceResponse> execute(FindInvoicesByFilterRequest request) {
        return switch (request.getType()) {
            case RENT_PAYMENT -> {
                Tenant tenant = tenantServicePort.findByUserId(UserContext.getUser().getId());

                Long entityId = tenant.getRentalContract().getId();

                yield invoiceServicePort
                        .findMatchableInvoices(request.getType(), entityId).stream()
                        .map(InvoiceResponse::of).toList();
            }
        };
    }
}
