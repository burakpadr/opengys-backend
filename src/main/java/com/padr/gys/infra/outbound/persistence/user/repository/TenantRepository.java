package com.padr.gys.infra.outbound.persistence.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.user.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    
    List<Tenant> findByRentalContractIsNull();
}
