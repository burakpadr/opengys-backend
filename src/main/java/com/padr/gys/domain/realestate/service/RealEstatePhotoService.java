package com.padr.gys.domain.realestate.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.common.property.AppProperty;
import com.padr.gys.domain.common.util.FileUtil;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.entity.RealEstatePhoto;
import com.padr.gys.domain.realestate.port.RealEstatePhotoServicePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePhotoPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RealEstatePhotoService implements RealEstatePhotoServicePort {

    private final RealEstatePhotoPersistencePort realEstatePhotoPersistencePort;

    private final AppProperty appProperty;

    @Override
    public List<RealEstatePhoto> findByRealEstateId(Long realEstateId) {
        return realEstatePhotoPersistencePort.findByRealEstateId(realEstateId);
    }

    @Override
    public List<RealEstatePhoto> createAll(List<RealEstatePhoto> realEstatePhotos) {
        realEstatePhotos.stream().forEach(realEstatePhoto -> {
            String folderPath = appProperty.getStorage().getRealEstateImagesPath() + "/"
                    + realEstatePhoto.getRealEstate().getId();

            FileUtil.createDirectoryIfNotExist(folderPath);

            String fileName = UUID.randomUUID().toString()
                    + realEstatePhoto.getImage().getOriginalFilename().split(".")[1];

            File image = new File(folderPath + "/" + fileName);

            try {
                realEstatePhoto.getImage().transferTo(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            realEstatePhoto.setPath(appProperty.getStorage().getRealEstateImagesRelativeUrl() + "/"
                    + realEstatePhoto.getRealEstate().getId() + "/" + fileName);
        });

        return realEstatePhotos;
    }

    @Override
    public void updateAll(RealEstate realEstate, List<RealEstatePhoto> realEstatePhotos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAll'");
    }
}
