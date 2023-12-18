package com.padr.gys.infra.outbound.persistence.deedowner.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.deedowner.entity.DeedOwner;

public interface DeedOwnerPersistencePort {
    
    Page<DeedOwner> findAll(Pageable pageable);

    Optional<DeedOwner> findById(Long id);

    Optional<DeedOwner> findByEmail(String email);

    Optional<DeedOwner> findByPhoneNumber(String phoneNumber);

    DeedOwner save(DeedOwner deedOwner);
}
