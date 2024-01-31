package com.padr.gys.domain.user.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.user.entity.Staff;

public interface StaffServicePort {

    Staff create(Staff staff);

    Long countDeedOwner();

    boolean isStaff(Long userId);

    Page<Staff> findByIsDeedOwner(Boolean isDeedOwner, Pageable pageable);

    Page<Staff> findBySearchTerm(String searchTerm, Pageable pageable);

    Staff findById(Long id);

    Staff findByUserId(Long userId);

    Staff changeActivity(Long staffId, Boolean isActive);

    Staff update(Staff oldStaff, Staff updateStaff);

    void delete(Staff staff);
}