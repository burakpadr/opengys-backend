package com.padr.gys.infra.inbound.rest.user.usecase;

import com.padr.gys.infra.outbound.persistence.user.port.TenantPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.user.model.response.TenantResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SearchTenantUseCase {
    
    private final TenantPersistencePort tenantPersistencePort;

    public Page<TenantResponse> execute(String searchTerm, Pageable pageable) {
        return tenantPersistencePort.findBySearchTerm(searchTerm, pageable).map(TenantResponse::of);
    }
}
