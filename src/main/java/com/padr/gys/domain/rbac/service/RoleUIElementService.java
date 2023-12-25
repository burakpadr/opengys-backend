package com.padr.gys.domain.rbac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.rbac.entity.RoleUIElement;
import com.padr.gys.domain.rbac.port.RoleUIElementServicePort;
import com.padr.gys.infra.outbound.persistence.rbac.port.RoleUIElementPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class RoleUIElementService implements RoleUIElementServicePort {
    
    private final RoleUIElementPersistencePort roleUIElementPersistencePort;

    @Override
    public List<RoleUIElement> findByRoleId(Long roleId) {
        return roleUIElementPersistencePort.findByRoleId(roleId);
    }

    @Override
    public List<RoleUIElement> saveAll(List<RoleUIElement> roleUIElements) {
        return roleUIElementPersistencePort.saveAll(roleUIElements);
    }

    @Override
    public void deleteAll(List<RoleUIElement> roleUIElements) {
        roleUIElements.stream().forEach(roleUIElement -> {
            roleUIElement.setIsActive(false);
        });

        roleUIElementPersistencePort.saveAll(roleUIElements);
    }
}
