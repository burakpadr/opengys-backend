package com.padr.gys.infra.inbound.rest.user.usecase;

import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.infra.outbound.persistence.user.port.TenantPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.user.model.response.TenantResponse;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class FindTenantByUserIdUseCase {

    private final TenantPersistencePort tenantPersistencePort;

    private final MessageSource messageSource;

    public TenantResponse execute(Long userId) {
        Tenant tenant = tenantPersistencePort.findByUserId(userId).orElseThrow(() -> new NoSuchElementException(
                messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

        return TenantResponse.of(tenant);
    }
}
