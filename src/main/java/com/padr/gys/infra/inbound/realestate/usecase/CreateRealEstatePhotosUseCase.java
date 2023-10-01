package com.padr.gys.infra.inbound.realestate.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.entity.RealEstatePhoto;
import com.padr.gys.domain.realestate.port.RealEstatePhotoServicePort;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;
import com.padr.gys.infra.inbound.realestate.model.request.RealEstatePhotoRequest;
import com.padr.gys.infra.inbound.realestate.model.response.RealEstatePhotoResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateRealEstatePhotosUseCase {

    private final RealEstateServicePort realEstateServicePort;
    private final RealEstatePhotoServicePort realEstatePhotoServicePort;

    public List<RealEstatePhotoResponse> execute(Long realEstateId, List<RealEstatePhotoRequest> requests) {
        RealEstate realEstate = realEstateServicePort.findByIdAndIsActive(realEstateId, true);

        List<RealEstatePhoto> realEstatePhotos = requests.stream().map(request -> {
            RealEstatePhoto realEstatePhoto = request.to();
            realEstatePhoto.setRealEstate(realEstate);

            return realEstatePhoto;
        }).toList();

        return realEstatePhotoServicePort.createAll(realEstatePhotos).stream().map(RealEstatePhotoResponse::of)
                .toList();
    }
}
