package com.padr.gys.domain.user.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.user.entity.Staff;

public interface StaffServicePort {

    Staff create(Staff staff);

    Long countDeedOwner();

    Page<Staff> findAll(Pageable pageable);

    Staff findById(Long id);

    Staff update(Staff oldStaff, Staff updateStaff);

    void delete(Long id);
}
