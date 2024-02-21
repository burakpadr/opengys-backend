package com.padr.gys.infra.outbound.persistence.user.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.infra.outbound.persistence.user.repository.TenantRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class TenantPersistencePortImpl implements TenantPersistencePort {
    
    private final TenantRepository tenantRepository;

    @Override
    public Optional<Tenant> findById(Long id) {
        return tenantRepository.findById(id);
    }

    @Override
    public Page<Tenant> findAll(Pageable pageable) {
        return tenantRepository.findAll(pageable);
    }

    @Override
    public List<Tenant> findByRentalContractIsNull() {
        return tenantRepository.findByRentalContractIsNull();
    }

    @Override
    public Tenant save(Tenant tenant) {
        return tenantRepository.save(tenant);
    }
}
