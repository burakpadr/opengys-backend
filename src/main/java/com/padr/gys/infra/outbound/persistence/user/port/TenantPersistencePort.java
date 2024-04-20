package com.padr.gys.infra.outbound.persistence.user.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.user.entity.Tenant;

public interface TenantPersistencePort {

    Tenant save(Tenant tenant);
    
    Optional<Tenant> findById(Long id);

    Optional<Tenant> findByUserId(Long userId);

    Page<Tenant> findAll(Pageable pageable);

    List<Tenant> findByRentalContractIsNull();

    Page<Tenant> findBySearchTerm(String searchTerm, Pageable pageable);
}
