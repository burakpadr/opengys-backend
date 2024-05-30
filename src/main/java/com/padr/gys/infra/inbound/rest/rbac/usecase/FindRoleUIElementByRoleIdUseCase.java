package com.padr.gys.infra.inbound.rest.rbac.usecase;

import java.util.List;

import com.padr.gys.infra.outbound.persistence.rbac.port.RoleUIElementPersistencePort;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.common.context.UserContext;
import com.padr.gys.infra.inbound.rest.rbac.model.response.RoleUIElementResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindRoleUIElementByRoleIdUseCase {

    private final RoleUIElementPersistencePort roleUIElementPersistencePort;

    public List<RoleUIElementResponse> execute(Long roleId) {
        if (UserContext.getIsStaff()) {
            return roleUIElementPersistencePort
                    .findByRoleId(roleId)
                    .stream()
                    .map(RoleUIElementResponse::of)
                    .toList();
        } else {
            return List.of();
        }
    }
}
