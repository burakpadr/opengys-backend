package com.padr.gys.infra.inbound.rest.user.usecase;

import com.padr.gys.infra.outbound.persistence.user.port.StaffPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.user.model.response.StaffResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllStaffAsPageUseCase {
    
    private final StaffPersistencePort staffPersistencePort;

    public Page<StaffResponse> execute(Pageable pageable) {
        return staffPersistencePort.findByIsDeedOwner(false, pageable).map(StaffResponse::of);
    }
}
