package com.padr.gys.infra.inbound.rest.user.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.port.StaffServicePort;
import com.padr.gys.infra.inbound.rest.user.model.response.StaffResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SearchStaffUseCase {
    
    private final StaffServicePort staffServicePort;

    public Page<StaffResponse> execute(String searchTerm, Pageable pageable) {
        return staffServicePort.findBySearchTerm(searchTerm, pageable).map(StaffResponse::of);
    } 
}
