package com.padr.gys.infra.inbound.user.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.port.StaffServicePort;
import com.padr.gys.infra.inbound.user.model.response.StaffResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllStaffAsPageUseCase {
    
    private final StaffServicePort staffServicePort;

    public Page<StaffResponse> execute(Pageable pageable) {
        return staffServicePort.findAll(pageable).map(StaffResponse::of);
    }
}
