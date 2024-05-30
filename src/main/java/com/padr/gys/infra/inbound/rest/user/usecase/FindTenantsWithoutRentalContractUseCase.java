package com.padr.gys.infra.inbound.rest.user.usecase;

import java.util.List;

import com.padr.gys.infra.outbound.persistence.user.port.TenantPersistencePort;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.user.model.response.TenantResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindTenantsWithoutRentalContractUseCase {

    private final TenantPersistencePort tenantPersistencePort;

    public List<TenantResponse> execute() {
        return tenantPersistencePort.findByRentalContractIsNull().stream().map(TenantResponse::of).toList();
    }
}
