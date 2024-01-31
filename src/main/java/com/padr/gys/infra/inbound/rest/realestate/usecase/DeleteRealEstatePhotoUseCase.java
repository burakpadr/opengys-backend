package com.padr.gys.infra.inbound.rest.realestate.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.port.RealEstatePhotoServicePort;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteRealEstatePhotoUseCase {
    
    private final RealEstateServicePort realEstateServicePort;
    private final RealEstatePhotoServicePort realEstatePhotoServicePort;

    public void execute(Long realEstateId, Long realEstatePhotoId) {
        realEstatePhotoServicePort.deleteById(realEstatePhotoId);
        
        realEstateServicePort.removeCoverPhoto(realEstateId);
    }
}
