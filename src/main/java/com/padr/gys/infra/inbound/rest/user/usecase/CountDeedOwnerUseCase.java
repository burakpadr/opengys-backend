package com.padr.gys.infra.inbound.rest.user.usecase;

import com.padr.gys.infra.outbound.persistence.user.port.StaffPersistencePort;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CountDeedOwnerUseCase {
    
    private final StaffPersistencePort staffPersistencePort;

    public Long execute() {
        return staffPersistencePort.countByIsDeedOwner(true);
    }
}
