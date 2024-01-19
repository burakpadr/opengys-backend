package com.padr.gys.infra.inbound.user.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.port.StaffServicePort;
import com.padr.gys.infra.inbound.user.model.response.StaffResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindStaffByIdUseCase {
    
    private final StaffServicePort staffServicePort;

    public StaffResponse execute(Long id) {
        return StaffResponse.of(staffServicePort.findById(id));
    }
}
