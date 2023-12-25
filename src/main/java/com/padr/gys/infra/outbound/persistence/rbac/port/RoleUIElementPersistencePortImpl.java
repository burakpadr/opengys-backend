package com.padr.gys.infra.outbound.persistence.rbac.port;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.entity.RoleUIElement;
import com.padr.gys.infra.outbound.persistence.rbac.repository.RoleUIElementRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class RoleUIElementPersistencePortImpl implements RoleUIElementPersistencePort {
    
    private final RoleUIElementRepository roleUIElementRepository;

    @Override
    public List<RoleUIElement> findByRoleId(Long roleId) {
        return roleUIElementRepository.findByRoleId(roleId);
    }

    @Override
    public List<RoleUIElement> saveAll(List<RoleUIElement> roleUIElements) {
        return roleUIElementRepository.saveAll(roleUIElements);
    }
}
