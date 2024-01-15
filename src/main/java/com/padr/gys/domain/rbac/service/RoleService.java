package com.padr.gys.domain.rbac.service;

import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.rbac.constant.RbacExceptionMessage;
import com.padr.gys.domain.rbac.entity.Role;
import com.padr.gys.domain.rbac.port.RoleServicePort;
import com.padr.gys.infra.outbound.persistence.rbac.port.RolePersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class RoleService implements RoleServicePort {

    private final RolePersistencePort rolePersistencePort;

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return rolePersistencePort.findAll(pageable);
    }

    @Override
    public Page<Role> search(String searchTerm, Pageable pageable) {
        return rolePersistencePort.findBySearchTerm(searchTerm, pageable);
    }

    @Override
    public Role findById(Long id) {
        return rolePersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(RbacExceptionMessage.ROLE_NOT_FOUND));
    }

    @Override
    public Role save(Role role) {
        return rolePersistencePort.save(role);
    }

    @Override
    public Role update(Role oldRole, Role newRole) {
        oldRole.setLabel(newRole.getLabel());

        return rolePersistencePort.save(oldRole);
    }

    @Override
    public void delete(Role role) {
        role.setIsDeleted(true);

        rolePersistencePort.save(role);
    }
}
