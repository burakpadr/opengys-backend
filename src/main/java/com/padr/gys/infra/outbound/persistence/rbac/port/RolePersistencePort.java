package com.padr.gys.infra.outbound.persistence.rbac.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.rbac.entity.Role;

public interface RolePersistencePort {
    
    Page<Role> findAll(Pageable pageable);

    Optional<Role> findById(Long id);

    Role save(Role role);
}
