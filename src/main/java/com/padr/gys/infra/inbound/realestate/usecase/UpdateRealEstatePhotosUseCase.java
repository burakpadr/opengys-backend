package com.padr.gys.infra.inbound.realestate.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.port.RealEstatePhotoServicePort;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;
import com.padr.gys.infra.inbound.realestate.model.request.RealEstatePhotoRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateRealEstatePhotosUseCase {

    private final RealEstateServicePort realEstateServicePort;
    private final RealEstatePhotoServicePort realEstatePhotoServicePort;

    public void execute(Long realEstateId, List<RealEstatePhotoRequest> requests) {
        RealEstate realEstate = realEstateServicePort.findByIdAndIsActive(realEstateId, true);

        realEstatePhotoServicePort.updateAll(realEstate,
                requests.stream().map(RealEstatePhotoRequest::to).toList());
    }
}
