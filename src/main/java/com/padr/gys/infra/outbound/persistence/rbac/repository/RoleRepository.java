package com.padr.gys.infra.outbound.persistence.rbac.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.rbac.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r "
            + "WHERE r.label ILIKE concat('%', :searchTerm, '%') ")
    Page<Role> findBySearchTerm(String searchTerm, Pageable pageable);
}
