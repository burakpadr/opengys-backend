package com.padr.gys.infra.inbound.realestate.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.entity.RealEstatePhoto;
import com.padr.gys.domain.realestate.port.RealEstatePhotoServicePort;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChangeRealEstateCoverPhotoUseCase {
    
    private final RealEstateServicePort realEstateServicePort;
    private final RealEstatePhotoServicePort realEstatePhotoServicePort;

    public void execute(Long realEstateId, Long realEstatePhotoId) {
        RealEstatePhoto coverPhoto = realEstatePhotoServicePort.findById(realEstatePhotoId);

        realEstateServicePort.changeCoverPhoto(realEstateId, coverPhoto);
    }
}
