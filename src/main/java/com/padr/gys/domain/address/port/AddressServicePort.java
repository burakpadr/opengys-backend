package com.padr.gys.domain.address.port;

import com.padr.gys.domain.address.entity.Address;

public interface AddressServicePort {

    Address create(Address address);

    Address update(Long id, Address address);

    void delete(Long id);
}
