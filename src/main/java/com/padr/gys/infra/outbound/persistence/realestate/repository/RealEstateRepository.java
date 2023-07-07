package com.padr.gys.infra.outbound.persistence.realestate.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.realestate.entity.RealEstate;

@Repository
public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {
    
    Optional<RealEstate> findByIdAndIsActive(Long id, Boolean isActive);

    Page<RealEstate> findByIsActive(Boolean isActive, Pageable pageable);

    Optional<RealEstate> findByNoAndIsActive(String no, Boolean isActive);
}
