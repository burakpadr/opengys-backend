package com.padr.gys.infra.inbound.realestate.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.port.RealEstateServicePort;
import com.padr.gys.infra.inbound.realestate.model.response.RealEstateResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindRealEstateByIdUseCase {
    
    private final RealEstateServicePort realEstateServicePort;

    public RealEstateResponse execute(Long id) {
        return RealEstateResponse.of(realEstateServicePort.findByIdAndIsActive(id, true));
    }
}
