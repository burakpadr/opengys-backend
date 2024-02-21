package com.padr.gys.domain.user.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.domain.user.port.TenantServicePort;
import com.padr.gys.infra.outbound.persistence.user.port.TenantPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class TenantService implements TenantServicePort {

    private final TenantPersistencePort tenantPersistencePort;

    private MessageSource messageSource;

    @Override
    public Tenant create(Tenant tenant) {
        return tenantPersistencePort.save(tenant);
    }

    @Override
    public Page<Tenant> findAll(Pageable pageable) {
        return tenantPersistencePort.findAll(pageable);
    }

    @Override
    public List<Tenant> findByRentalContractIsNull() {
        return tenantPersistencePort.findByRentalContractIsNull();
    }

    @Override
    public Tenant findById(Long id) {
        return tenantPersistencePort.findById(id).orElseThrow(() -> new NoSuchElementException(
                messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));
    }

    @Override
    public void delete(Tenant tenant) {
        tenant.setIsDeleted(true);

        tenantPersistencePort.save(tenant);
    }

    @Override
    public Tenant update(Tenant oldTenant, Tenant updateTenant) {
        oldTenant.setUser(updateTenant.getUser());

        return tenantPersistencePort.save(oldTenant);
    }
}
