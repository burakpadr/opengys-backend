package com.padr.gys.domain.realestate.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.common.constant.AllowedFileType;
import com.padr.gys.domain.common.property.AppProperty;
import com.padr.gys.domain.common.util.FileUtil;
import com.padr.gys.domain.realestate.entity.RealEstatePhoto;
import com.padr.gys.domain.realestate.port.RealEstatePhotoServicePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePhotoPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class RealEstatePhotoService implements RealEstatePhotoServicePort {

    private final RealEstatePhotoPersistencePort realEstatePhotoPersistencePort;

    private final AppProperty appProperty;

    @Override
    public List<RealEstatePhoto> findByRealEstateId(Long realEstateId) {
        return realEstatePhotoPersistencePort.findByRealEstateId(realEstateId);
    }

    @Override
    public List<RealEstatePhoto> createAll(List<RealEstatePhoto> realEstatePhotos) {
        realEstatePhotos.stream().forEach(realEstatePhoto -> {
            realEstatePhoto.setIsActive(true);

            String folderPath = appProperty.getStorage().getRealEstateImagesPath() + "/"
                    + realEstatePhoto.getRealEstate().getId();

            FileUtil.createDirectoryIfNotExist(folderPath);

            String extension = Objects.nonNull(realEstatePhoto.getImage().getOriginalFilename())
                    ? FileUtil.getFileExtension(realEstatePhoto.getImage().getOriginalFilename())
                    : AllowedFileType.IMAGE_JPG.getExtension();

            String fileName = UUID.randomUUID().toString() + "." + extension;

            File image = new File(folderPath + "/" + fileName);

            try {
                realEstatePhoto.getImage().transferTo(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            realEstatePhoto.setPath(appProperty.getStorage().getRealEstateImagesRelativeUrl() + "/"
                    + realEstatePhoto.getRealEstate().getId() + "/" + fileName);
        });

        return realEstatePhotoPersistencePort.saveAll(realEstatePhotos);
    }
}
