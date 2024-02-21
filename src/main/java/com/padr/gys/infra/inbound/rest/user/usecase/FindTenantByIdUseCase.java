package com.padr.gys.infra.inbound.rest.user.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.port.TenantServicePort;
import com.padr.gys.infra.inbound.rest.user.model.response.TenantResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindTenantByIdUseCase {

    private final TenantServicePort tenantServicePort;

    public TenantResponse execute(Long id) {
        return TenantResponse.of(tenantServicePort.findById(id));
    }
}
