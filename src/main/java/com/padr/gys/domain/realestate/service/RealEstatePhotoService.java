package com.padr.gys.domain.realestate.service;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.common.property.AppProperty;
import com.padr.gys.domain.common.util.FileUtil;
import com.padr.gys.domain.common.util.ImageUtil;
import com.padr.gys.domain.realestate.entity.RealEstatePhoto;
import com.padr.gys.domain.realestate.port.RealEstatePhotoServicePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePhotoPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class RealEstatePhotoService implements RealEstatePhotoServicePort {

    private final RealEstatePhotoPersistencePort realEstatePhotoPersistencePort;

    private final MessageSource messageSource;

    private final AppProperty appProperty;
    private final FileUtil fileUtil;
    private final ImageUtil imageUtil;

    @Override
    public List<RealEstatePhoto> findByRealEstateId(Long realEstateId) {
        return realEstatePhotoPersistencePort.findByRealEstateId(realEstateId);
    }

    @Override
    public List<RealEstatePhoto> createAll(List<RealEstatePhoto> realEstatePhotos) {
        Long realEstateId = Objects.nonNull(realEstatePhotos) && realEstatePhotos.size() > 0
                ? realEstatePhotos.get(0).getRealEstate().getId()
                : null;

        realEstatePhotos.stream().forEach(realEstatePhoto -> {
            String fileName = UUID.randomUUID().toString();
            String extension = fileUtil.getFileExtension(realEstatePhoto.getImage().getOriginalFilename());

            String path = String.format("%s/%d/%s.%s", appProperty.getStorage().getRealEstateImagesPath(), realEstateId,
                    fileName, extension);

            File image = fileUtil.upload(path, realEstatePhoto.getImage());

            imageUtil.resizeImages(image, appProperty.getImage().getWidth(), appProperty.getImage().getHeight());

            realEstatePhoto.setPath(appProperty.getStorage().getRealEstateImagesRelativeUrl() + "/"
                    + realEstateId + "/" + fileName + "." + extension);
        });

        realEstatePhotoPersistencePort.saveAll(realEstatePhotos);

        return realEstatePhotos;
    }

    @Override
    public void deleteById(Long id) {
        RealEstatePhoto realEstatePhoto = findById(id);

        realEstatePhoto.setIsDeleted(true);

        realEstatePhotoPersistencePort.saveAll(List.of(realEstatePhoto));
    }

    public RealEstatePhoto findById(Long id) {
        return realEstatePhotoPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("realestate.photo.not-found", null, LocaleContextHolder.getLocale())));
    }
}
