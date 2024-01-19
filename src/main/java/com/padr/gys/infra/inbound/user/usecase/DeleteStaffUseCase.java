package com.padr.gys.infra.inbound.user.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.domain.user.port.StaffServicePort;
import com.padr.gys.domain.user.port.UserServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteStaffUseCase {
    
    private final StaffServicePort staffServicePort;
    private final UserServicePort userServicePort;

    public void execute(Long id) {
        Staff staff = staffServicePort.findById(id);

        staffServicePort.delete(staff);
        userServicePort.delete(id);
    }
}
