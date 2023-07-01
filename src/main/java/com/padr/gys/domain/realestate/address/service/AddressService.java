package com.padr.gys.domain.realestate.address.service;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.realestate.address.entity.Address;
import com.padr.gys.domain.realestate.address.exception.AddressNotFoundException;
import com.padr.gys.domain.realestate.address.port.AddressServicePort;
import com.padr.gys.infra.outbound.persistence.address.port.AddressPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService implements AddressServicePort {

    private final AddressPersistencePort addressPersistencePort;

    @Override
    public Address create(Address address) {
        address.setIsActive(true);

        return addressPersistencePort.save(address);
    }

    @Override
    public Address update(Long id, Address address) {
        Address oldAddress = findById(id);

        oldAddress.setLatitude(address.getLatitude());
        oldAddress.setLongitude(address.getLongitude());
        oldAddress.setCityName(address.getCityName());
        oldAddress.setDistrictName(address.getDistrictName());
        oldAddress.setNeighborhoodName(address.getNeighborhoodName());
        oldAddress.setPostCode(address.getPostCode());
        oldAddress.setOpenAddress(address.getOpenAddress());

        return addressPersistencePort.save(oldAddress);
    }

    @Override
    public void delete(Long id) {
        Address address = findById(id);
        address.setIsActive(false);

        addressPersistencePort.save(address);
    }

    private Address findById(Long id) {
        return addressPersistencePort.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
    }
}
