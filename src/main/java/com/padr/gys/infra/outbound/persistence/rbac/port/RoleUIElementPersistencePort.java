package com.padr.gys.infra.outbound.persistence.rbac.port;

import java.util.List;

import com.padr.gys.domain.rbac.entity.RoleUIElement;

public interface RoleUIElementPersistencePort {
    
    List<RoleUIElement> findByRoleId(Long roleId);

    List<RoleUIElement> saveAll(List<RoleUIElement> roleUIElements);
}
