package com.padr.gys.infra.inbound.rest.user.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.domain.user.port.TenantServicePort;
import com.padr.gys.domain.user.port.UserServicePort;
import com.padr.gys.infra.inbound.rest.user.model.request.UpdateTenantRequest;
import com.padr.gys.infra.inbound.rest.user.model.response.TenantResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateTenantUseCase {
    
    private final TenantServicePort tenantServicePort;
    private final UserServicePort userServicePort;

    public TenantResponse execute(Long id, UpdateTenantRequest request) {
        Tenant oldTenant = tenantServicePort.findById(id);

        Tenant updateTenant = Tenant.builder()
                .user(request.getUser().to(null))
                .build();

        userServicePort.update(oldTenant.getUser(), request.getUser().to(null));
        tenantServicePort.update(oldTenant, updateTenant);

        return TenantResponse.of(oldTenant);
    }
}
