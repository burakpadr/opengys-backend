package com.padr.gys.infra.outbound.persistence.address.port;

import java.util.Optional;

import com.padr.gys.domain.address.entity.Address;

public interface AddressPersistencePort {
    
    Address save(Address address);

    Optional<Address> findById(Long id);
}
