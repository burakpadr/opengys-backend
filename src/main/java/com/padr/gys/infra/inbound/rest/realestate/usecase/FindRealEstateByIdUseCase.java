package com.padr.gys.infra.inbound.rest.realestate.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.port.RealEstateServicePort;
import com.padr.gys.infra.inbound.rest.realestate.model.response.RealEstateDetailResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindRealEstateByIdUseCase {
    
    private final RealEstateServicePort realEstateServicePort;

    public RealEstateDetailResponse execute(Long id) {
        return RealEstateDetailResponse.of(realEstateServicePort.findById(id));
    }
}
