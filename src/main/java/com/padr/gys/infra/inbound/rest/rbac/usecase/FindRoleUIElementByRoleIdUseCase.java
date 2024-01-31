package com.padr.gys.infra.inbound.rest.rbac.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.port.RoleUIElementServicePort;
import com.padr.gys.infra.inbound.rest.rbac.model.response.RoleUIElementResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindRoleUIElementByRoleIdUseCase {

    private final RoleUIElementServicePort roleUIElementServicePort;

    public List<RoleUIElementResponse> execute(Long roleId) {
        return roleUIElementServicePort
                .findByRoleId(roleId)
                .stream()
                .map(RoleUIElementResponse::of)
                .toList();
    }
}
