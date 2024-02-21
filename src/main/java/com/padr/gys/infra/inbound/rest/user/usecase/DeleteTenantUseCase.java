package com.padr.gys.infra.inbound.rest.user.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.domain.user.port.TenantServicePort;
import com.padr.gys.domain.user.port.UserServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteTenantUseCase {
    
    private final TenantServicePort tenantServicePort;
    private final UserServicePort userServicePort;

    public void execute(Long id) {
        Tenant tenant = tenantServicePort.findById(id);

        userServicePort.delete(tenant.getUser().getId());
        tenantServicePort.delete(tenant);
    }
}
