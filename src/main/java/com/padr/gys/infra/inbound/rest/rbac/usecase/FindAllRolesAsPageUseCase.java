package com.padr.gys.infra.inbound.rest.rbac.usecase;

import com.padr.gys.infra.outbound.persistence.rbac.port.RolePersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.rbac.model.response.RoleResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllRolesAsPageUseCase {
    
    private final RolePersistencePort rolePersistencePort;

    public Page<RoleResponse> execute(Pageable pageable) {
        return rolePersistencePort.findAll(pageable).map(RoleResponse::of);
    }
}
