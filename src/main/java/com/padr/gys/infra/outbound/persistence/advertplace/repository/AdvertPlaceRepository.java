package com.padr.gys.infra.outbound.persistence.advertplace.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.advertplace.entity.AdvertPlace;

@Repository
public interface AdvertPlaceRepository extends JpaRepository<AdvertPlace, Long> {
    
    Page<AdvertPlace> findByIsActive(Boolean isActive, Pageable pageable);

    Optional<AdvertPlace> findByIdAndIsActive(Long id, Boolean isActive);
}
