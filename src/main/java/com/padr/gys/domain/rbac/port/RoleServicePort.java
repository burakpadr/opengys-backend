package com.padr.gys.domain.rbac.port;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.rbac.entity.Role;

public interface RoleServicePort {
    
    Page<Role> findAll(Pageable pageable);

    List<Role> findAll();

    Page<Role> search(String searchTerm, Pageable pageable);

    Role findById(Long id);

    Role save(Role role);

    Role update(Role oldRole, Role newRole);

    void delete(Role role);
}
