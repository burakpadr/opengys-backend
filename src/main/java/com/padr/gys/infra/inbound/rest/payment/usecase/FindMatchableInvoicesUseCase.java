package com.padr.gys.infra.inbound.rest.payment.usecase;

import java.util.List;
import java.util.NoSuchElementException;

import com.padr.gys.infra.outbound.persistence.payment.port.InvoicePersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.TenantPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.infra.inbound.common.context.UserContext;
import com.padr.gys.infra.inbound.rest.payment.model.request.FindInvoicesByFilterRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.InvoiceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindMatchableInvoicesUseCase {

    private final InvoicePersistencePort invoicePersistencePort;
    private final TenantPersistencePort tenantPersistencePort;

    private final MessageSource messageSource;

    public List<InvoiceResponse> execute(FindInvoicesByFilterRequest request) {
        return switch (request.getType()) {
            case RENT_PAYMENT -> {
                Tenant tenant = tenantPersistencePort.findByUserId(UserContext.getUser().getId()).orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

                Long entityId = tenant.getRentalContract().getId();

                yield invoicePersistencePort
                        .findMatchableInvoices(request.getType(), entityId).stream()
                        .map(InvoiceResponse::of).toList();
            }
        };
    }
}
