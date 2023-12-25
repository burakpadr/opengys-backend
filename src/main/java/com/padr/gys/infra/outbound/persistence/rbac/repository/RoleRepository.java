package com.padr.gys.infra.outbound.persistence.rbac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.rbac.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
