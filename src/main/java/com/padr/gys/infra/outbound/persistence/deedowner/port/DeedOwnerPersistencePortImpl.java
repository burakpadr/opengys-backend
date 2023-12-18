package com.padr.gys.infra.outbound.persistence.deedowner.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.deedowner.entity.DeedOwner;
import com.padr.gys.infra.outbound.persistence.deedowner.repository.DeedOwnerRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class DeedOwnerPersistencePortImpl implements DeedOwnerPersistencePort {
    
    private final DeedOwnerRepository deedOwnerRepository;

    @Override
    public Page<DeedOwner> findAll(Pageable pageable) {
        return deedOwnerRepository.findAll(pageable);
    }

    @Override
    public Optional<DeedOwner> findById(Long id) {
        return deedOwnerRepository.findById(id);
    }

    @Override
    public Optional<DeedOwner> findByEmail(String email) {
        return deedOwnerRepository.findByEmail(email);
    }

    @Override
    public Optional<DeedOwner> findByPhoneNumber(String phoneNumber) {
        return deedOwnerRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public DeedOwner save(DeedOwner deedOwner) {
        return deedOwnerRepository.save(deedOwner);
    }
}
