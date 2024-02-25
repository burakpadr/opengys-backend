package com.padr.gys.infra.inbound.rest.user.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.port.TenantServicePort;
import com.padr.gys.infra.inbound.rest.user.model.response.TenantResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SearchTenantUseCase {
    
    private final TenantServicePort tenantServicePort;

    public Page<TenantResponse> execute(String searchTerm, Pageable pageable) {
        return tenantServicePort.findBySearchTerm(searchTerm, pageable).map(TenantResponse::of);
    }
}
