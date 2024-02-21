package com.padr.gys.infra.inbound.rest.user.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.domain.user.entity.User;
import com.padr.gys.domain.user.port.TenantServicePort;
import com.padr.gys.domain.user.port.UserServicePort;
import com.padr.gys.infra.inbound.rest.user.model.request.CreateTenantRequest;
import com.padr.gys.infra.inbound.rest.user.model.response.TenantResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateTenantUseCase {

    private final TenantServicePort tenantServicePort;
    private final UserServicePort userServicePort;

    public TenantResponse execute(CreateTenantRequest request) {
        User user = userServicePort.create(request.getUser().to(null));

        Tenant tenant = Tenant.builder()
                .user(user)
                .build();

        return TenantResponse.of(tenantServicePort.create(tenant));
    }
}
