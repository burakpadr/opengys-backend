package com.padr.gys.infra.outbound.persistence.advert.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.advert.entity.Advert;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {
    
    Page<Advert> findByRealEstateId(Long realEstateId, Pageable pageable);

    List<Advert> findByRealEstateId(Long realEstateId);

    Optional<Advert> findById(Long id);
}
