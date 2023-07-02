package com.padr.gys.infra.inbound.realestate.information.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.information.port.RealEstateServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteRealEstateUseCase {
    
    private final RealEstateServicePort realEstateServicePort;

    public void execute(Long realEstateId) {
        realEstateServicePort.delete(realEstateId);
    } 
}
