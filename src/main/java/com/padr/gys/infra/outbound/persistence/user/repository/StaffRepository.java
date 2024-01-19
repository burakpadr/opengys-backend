package com.padr.gys.infra.outbound.persistence.user.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.user.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    
    Long countByIsDeedOwner(Boolean isDeedOwner);

    Optional<Staff> findByUserId(Long userId);

    Page<Staff> findByIsDeedOwner(Boolean isDeedOwner, Pageable pageable);

    @Query("SELECT s FROM Staff s " +
        "WHERE " + 
        "(s.user.name || ' ' || s.user.surname ILIKE %:searchTerm% " +
        "OR s.user.email ILIKE %:searchTerm% " +
        "OR (s.user.role IS NULL OR s.user.role.label ILIKE %:searchTerm%)) " +
        "AND s.isDeedOwner = false")
    Page<Staff> findBySearchTerm(String searchTerm, Pageable pageable);
}
