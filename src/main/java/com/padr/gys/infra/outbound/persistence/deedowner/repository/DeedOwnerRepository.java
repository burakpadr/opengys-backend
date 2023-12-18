package com.padr.gys.infra.outbound.persistence.deedowner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.deedowner.entity.DeedOwner;

@Repository
public interface DeedOwnerRepository extends JpaRepository<DeedOwner, Long> {
    
    Optional<DeedOwner> findByEmail(String email);

    Optional<DeedOwner> findByPhoneNumber(String phoneNumber);
}
