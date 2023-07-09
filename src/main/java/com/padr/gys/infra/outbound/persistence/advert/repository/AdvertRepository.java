package com.padr.gys.infra.outbound.persistence.advert.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.advert.entity.Advert;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {
    
    Page<Advert> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive, Pageable pageable);

    Optional<Advert> findByIdAndIsActive(Long id, Boolean isActive);
}
