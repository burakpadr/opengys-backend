package com.padr.gys.domain.user.port;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.user.entity.Tenant;

public interface TenantServicePort {

    Tenant create(Tenant tenant);
    
    Page<Tenant> findAll(Pageable pageable);

    List<Tenant> findByRentalContractIsNull();

    Tenant findById(Long id);

    Tenant update(Tenant oldTenant, Tenant updateTenant);

    void delete(Tenant tenant);
}
