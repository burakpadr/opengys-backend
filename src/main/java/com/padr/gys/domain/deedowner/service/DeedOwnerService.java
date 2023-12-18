package com.padr.gys.domain.deedowner.service;

import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.deedowner.constant.DeedOwnerExceptionMessage;
import com.padr.gys.domain.deedowner.entity.DeedOwner;
import com.padr.gys.domain.deedowner.port.DeedOwnerServicePort;
import com.padr.gys.infra.outbound.persistence.deedowner.port.DeedOwnerPersistencePort;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class DeedOwnerService implements DeedOwnerServicePort {

    private final DeedOwnerPersistencePort deedOwnerPersistencePort;

    @Override
    public Page<DeedOwner> findAll(Pageable pageable) {
        return deedOwnerPersistencePort.findAll(pageable);
    }

    @Override
    public DeedOwner findById(Long id) {
        return deedOwnerPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(DeedOwnerExceptionMessage.DEED_OWNER_NOT_FOUND));
    }

    @Override
    public DeedOwner create(DeedOwner deedOwner) {
        throwExceptionIfEmailIsDuplicated(deedOwner.getEmail());
        throwExceptionIfPhoneNumberIsDuplicated(deedOwner.getPhoneNumber());

        return deedOwnerPersistencePort.save(deedOwner);
    }

    @Override
    public DeedOwner update(DeedOwner oldDeedOwner, DeedOwner newDeedOwner) {
        if (!oldDeedOwner.getEmail().equals(newDeedOwner.getEmail()))
            throwExceptionIfEmailIsDuplicated(newDeedOwner.getEmail());

        if (!oldDeedOwner.getPhoneNumber().equals(newDeedOwner.getPhoneNumber()))
            throwExceptionIfPhoneNumberIsDuplicated(newDeedOwner.getPhoneNumber());

        oldDeedOwner.setTitle(newDeedOwner.getTitle());
        oldDeedOwner.setEmail(newDeedOwner.getEmail());
        oldDeedOwner.setPhoneNumber(newDeedOwner.getPhoneNumber());
        oldDeedOwner.setAddress(newDeedOwner.getAddress());

        return deedOwnerPersistencePort.save(oldDeedOwner);
    }

    @Override
    public void delete(DeedOwner deedOwner) {
        deedOwner.setIsActive(false);

        deedOwnerPersistencePort.save(deedOwner);
    }

    private void throwExceptionIfEmailIsDuplicated(String email) {
        deedOwnerPersistencePort.findByEmail(email)
                .ifPresent(u -> {
                    throw new EntityExistsException(
                            DeedOwnerExceptionMessage.DEED_OWNER_ALREADY_EXIST_ASSOCIATED_WITH_EMAIL);
                });

    }

    private void throwExceptionIfPhoneNumberIsDuplicated(String phoneNumber) {
        deedOwnerPersistencePort.findByEmail(phoneNumber)
                .ifPresent(u -> {
                    throw new EntityExistsException(
                            DeedOwnerExceptionMessage.DEED_OWNER_ALREADY_EXIST_ASSOCIATED_WITH_PHONE);
                });

    }
}
