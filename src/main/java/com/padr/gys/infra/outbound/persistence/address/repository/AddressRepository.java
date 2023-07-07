package com.padr.gys.infra.outbound.persistence.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.address.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
