package com.padr.gys.infra.inbound.rest.rbac.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.port.RoleServicePort;
import com.padr.gys.infra.inbound.rest.rbac.model.response.RoleResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindRoleByIdUseCase {
    
    private final RoleServicePort roleServicePort;

    public RoleResponse execute(Long id) {
        return RoleResponse.of(roleServicePort.findById(id));
    }
}
