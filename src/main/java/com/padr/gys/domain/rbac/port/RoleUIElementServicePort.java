package com.padr.gys.domain.rbac.port;

import java.util.List;

import com.padr.gys.domain.rbac.entity.RoleUIElement;

public interface RoleUIElementServicePort {
    
    List<RoleUIElement> findByRoleId(Long roleId);

    List<RoleUIElement> saveAll(List<RoleUIElement> roleUIElements);
    
    void deleteAll(List<RoleUIElement> roleUIElements);
}
