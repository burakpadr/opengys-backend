package com.padr.gys.infra.outbound.persistence.advertplace.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.advertplace.entity.AdvertPlace;

@Repository
public interface AdvertPlaceRepository extends JpaRepository<AdvertPlace, Long> {

    Optional<AdvertPlace> findById(Long id);

    @Query("SELECT a FROM AdvertPlace a "
            + "WHERE a.name ILIKE concat('%', :searchTerm, '%') ")
    Page<AdvertPlace> findBySearchTerm(String searchTerm, Pageable pageable);
}
