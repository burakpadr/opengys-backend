package com.padr.gys.infra.inbound.rest.rbac.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.port.RoleServicePort;
import com.padr.gys.infra.inbound.rest.rbac.model.response.RoleResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SearchRoleUseCase {
    
    private final RoleServicePort roleServicePort;

    public Page<RoleResponse> execute(String searchTerm, Pageable pageable) {
        return roleServicePort.search(searchTerm, pageable).map(RoleResponse::of);
    }
}
