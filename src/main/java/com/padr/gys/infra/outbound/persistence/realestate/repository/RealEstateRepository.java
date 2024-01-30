package com.padr.gys.infra.outbound.persistence.realestate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.realestate.entity.RealEstate;

@Repository
public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {

    Optional<RealEstate> findByNo(String no);
}
