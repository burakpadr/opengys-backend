package com.padr.gys.infra.inbound.rest.payment.usecase;

import com.padr.gys.infra.outbound.persistence.payment.port.PaymentDeclarationPersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.TenantPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.common.context.UserContext;
import com.padr.gys.infra.inbound.rest.payment.model.request.FindPaymentDeclarationRequest;
import com.padr.gys.infra.inbound.rest.payment.model.response.PaymentDeclarationResponse;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class FindPaymentDeclarationsByFilterUseCase {

    private final PaymentDeclarationPersistencePort paymentDeclarationPersistencePort;
    private final TenantPersistencePort tenantPersistencePort;

    private final MessageSource messageSource;

    public Page<PaymentDeclarationResponse> execute(Pageable pageable, FindPaymentDeclarationRequest request) {
        if (UserContext.getIsStaff())
            return paymentDeclarationPersistencePort
                    .findByFilter(pageable, request.getInvoiceType(), request.getApprovementStatus(), null)
                    .map(PaymentDeclarationResponse::of);
        else {
            Long ownerId = tenantPersistencePort.findByUserId(UserContext.getUser().getId()).orElseThrow(() -> new NoSuchElementException(
                    messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale()))).getId();

            return paymentDeclarationPersistencePort
                    .findByFilter(pageable, request.getInvoiceType(), request.getApprovementStatus(), ownerId)
                    .map(PaymentDeclarationResponse::of);
        }
    }
}
