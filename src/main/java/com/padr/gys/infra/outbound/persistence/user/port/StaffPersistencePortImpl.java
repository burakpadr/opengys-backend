package com.padr.gys.infra.outbound.persistence.user.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.infra.outbound.persistence.user.repository.StaffRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class StaffPersistencePortImpl implements StaffPersistencePort {
    
    private final StaffRepository staffRepository;

    @Override
    public Long countByIsDeedOwner(Boolean isDeedOwner) {
        return staffRepository.countByIsDeedOwner(isDeedOwner);
    }

    @Override
    public Page<Staff> findAll(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }

    @Override
    public Optional<Staff> findById(Long id) {
        return staffRepository.findById(id);
    }

    @Override
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }
}
