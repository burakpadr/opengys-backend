package com.padr.gys.domain.user.service;

import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.user.constant.UserExceptionMessage;
import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.domain.user.port.StaffServicePort;
import com.padr.gys.infra.outbound.persistence.user.port.StaffPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class StaffService implements StaffServicePort {

    private final StaffPersistencePort staffPersistencePort;

    @Override
    public Staff create(Staff staff) {
        staff.setIsActive(true);

        return staffPersistencePort.save(staff);
    }

    @Override
    public Long countDeedOwner() {
        return staffPersistencePort.countByIsDeedOwner(true);
    }

    @Override
    public boolean isStaff(Long userId) {
        return staffPersistencePort.findByUserId(userId).isPresent();
    }

    @Override
    public Page<Staff> findByIsDeedOwner(Boolean isDeedOwner, Pageable pageable) {
        return staffPersistencePort.findByIsDeedOwner(isDeedOwner, pageable);
    }

    @Override
    public Staff findById(Long id) {
        return staffPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(UserExceptionMessage.USER_NOT_FOUND));
    }

    @Override
    public Staff update(Staff oldStaff, Staff updateStaff) {
        oldStaff.setIsDeedOwner(updateStaff.getIsDeedOwner());

        return staffPersistencePort.save(oldStaff);
    }

    @Override
    public void delete(Staff staff) {
        staff.setIsDeleted(true);

        staffPersistencePort.save(staff);
    }

    @Override
    public Staff findByUserId(Long userId) {
        return staffPersistencePort.findByUserId(userId)
                .orElseThrow(() -> new NoSuchElementException(UserExceptionMessage.USER_NOT_FOUND));
    }

    @Override
    public Staff changeActivity(Long staffId, Boolean isActive) {
        Staff staff = findById(staffId);

        staff.setIsActive(isActive);

        return staffPersistencePort.save(staff);
    }

    @Override
    public Page<Staff> findBySearchTerm(String searchTerm, Pageable pageable) {
        return staffPersistencePort.findBySearchTerm(searchTerm, pageable);
    }
}
