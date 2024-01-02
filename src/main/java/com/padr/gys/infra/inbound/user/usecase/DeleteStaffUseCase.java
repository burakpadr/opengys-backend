package com.padr.gys.infra.inbound.user.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.port.StaffServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteStaffUseCase {
    
    private StaffServicePort staffServicePort;

    public void execute(Long id) {
        staffServicePort.delete(id);
    }
}
