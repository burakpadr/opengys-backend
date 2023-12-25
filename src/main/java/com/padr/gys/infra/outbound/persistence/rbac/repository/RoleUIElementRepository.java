package com.padr.gys.infra.outbound.persistence.rbac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.rbac.entity.RoleUIElement;

@Repository
public interface RoleUIElementRepository extends JpaRepository<RoleUIElement, Long> {

    List<RoleUIElement> findByRoleId(Long roleId);
}
