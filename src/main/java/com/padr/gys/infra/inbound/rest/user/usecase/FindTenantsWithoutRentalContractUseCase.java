package com.padr.gys.infra.inbound.rest.user.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.port.TenantServicePort;
import com.padr.gys.infra.inbound.rest.user.model.response.TenantResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindTenantsWithoutRentalContractUseCase {

    private final TenantServicePort tenantServicePort;

    public List<TenantResponse> execute() {
        return tenantServicePort.findByRentalContractIsNull().stream().map(TenantResponse::of).toList();
    }
}
