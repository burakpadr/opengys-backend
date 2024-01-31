package com.padr.gys.infra.inbound.rest.user.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.port.StaffServicePort;
import com.padr.gys.infra.inbound.rest.user.model.response.StaffResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindStaffByUserIdUseCase {
    
    private final StaffServicePort staffServicePort;

    public StaffResponse execute(Long userId) {
        return StaffResponse.of(staffServicePort.findByUserId(userId));
    }
}
