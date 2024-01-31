package com.padr.gys.infra.inbound.rest.rbac.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.entity.Role;
import com.padr.gys.domain.rbac.port.RoleServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteRoleUseCase {
    
    private final RoleServicePort roleServicePort;

    public void execute(Long roleId) {
        Role role = roleServicePort.findById(roleId);

        roleServicePort.delete(role);
    }
}
