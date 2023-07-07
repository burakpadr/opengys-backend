package com.padr.gys.infra.outbound.persistence.address.port;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.address.entity.Address;
import com.padr.gys.infra.outbound.persistence.address.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddressPersistencePortImpl implements AddressPersistencePort {
    
    private final AddressRepository addressRepository;

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }
}
