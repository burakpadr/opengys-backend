package com.padr.gys.infra.outbound.persistence.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.user.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    
    Long countByIsDeedOwner(Boolean isDeedOwner);
}
