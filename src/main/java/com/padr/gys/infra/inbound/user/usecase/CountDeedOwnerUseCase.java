package com.padr.gys.infra.inbound.user.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.port.StaffServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CountDeedOwnerUseCase {
    
    private final StaffServicePort staffServicePort;

    public Long execute() {
        return staffServicePort.countDeedOwner();
    }
}
