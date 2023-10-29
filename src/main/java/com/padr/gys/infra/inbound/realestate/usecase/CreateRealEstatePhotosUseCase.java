package com.padr.gys.infra.inbound.realestate.usecase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.entity.RealEstatePhoto;
import com.padr.gys.domain.realestate.port.RealEstatePhotoServicePort;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateRealEstatePhotosUseCase {

    private final RealEstateServicePort realEstateServicePort;
    private final RealEstatePhotoServicePort realEstatePhotoServicePort;

    public void execute(Long realEstateId, List<MultipartFile> images) {
        RealEstate realEstate = realEstateServicePort.findByIdAndIsActive(realEstateId, true);

        List<RealEstatePhoto> realEstatePhotos = new ArrayList<>();

        images.stream().forEach(image -> {
            RealEstatePhoto realEstatePhoto = RealEstatePhoto.builder()
                    .realEstate(realEstate)
                    .image(image)
                    .build();

            realEstatePhotos.add(realEstatePhoto);
        });

        realEstatePhotoServicePort.createAll(realEstatePhotos);
    }
}
