package com.padr.gys.infra.outbound.persistence.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.user.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

    List<Tenant> findByRentalContractIsNull();

    @Query("SELECT t FROM Tenant t " +
            "WHERE " +
            "t.user.name || ' ' || t.user.surname ILIKE %:searchTerm% " +
            "OR t.user.email ILIKE %:searchTerm% ")
    Page<Tenant> findBySearchTerm(String searchTerm, Pageable pageable);

    Optional<Tenant> findByUserId(Long userId);
}
