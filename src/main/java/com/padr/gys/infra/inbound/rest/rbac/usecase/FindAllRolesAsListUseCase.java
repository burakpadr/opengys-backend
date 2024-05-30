package com.padr.gys.infra.inbound.rest.rbac.usecase;

import java.util.List;

import com.padr.gys.infra.outbound.persistence.rbac.port.RolePersistencePort;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.rbac.model.response.RoleResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllRolesAsListUseCase {
    
    private final RolePersistencePort rolePersistencePort;

    public List<RoleResponse> execute() {
        return rolePersistencePort.findAll().stream().map(RoleResponse::of).toList();
    }
}
