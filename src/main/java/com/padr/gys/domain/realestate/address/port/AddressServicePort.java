package com.padr.gys.domain.realestate.address.port;

import com.padr.gys.domain.realestate.address.entity.Address;

public interface AddressServicePort {

    Address create(Address address);

    Address update(Long id, Address address);

    void delete(Long id);
}
