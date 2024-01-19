package com.padr.gys.infra.inbound.rbac.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.port.RoleServicePort;
import com.padr.gys.infra.inbound.rbac.model.response.RoleResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllRolesAsListUseCase {
    
    private final RoleServicePort roleServicePort;

    public List<RoleResponse> execute() {
        return roleServicePort.findAll().stream().map(RoleResponse::of).toList();
    }
}
