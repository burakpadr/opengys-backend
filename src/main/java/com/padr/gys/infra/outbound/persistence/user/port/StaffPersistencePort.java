package com.padr.gys.infra.outbound.persistence.user.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.user.entity.Staff;

public interface StaffPersistencePort {
    
    Long countByIsDeedOwner(Boolean isDeedOwner);

    Page<Staff> findAll(Pageable pageable);

    Optional<Staff> findById(Long id);

    Staff save(Staff staff);
}
