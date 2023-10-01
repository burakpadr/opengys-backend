package com.padr.gys.domain.realestate.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.entity.RealEstatePhoto;
import com.padr.gys.domain.realestate.exception.DuplicateRealEstateCoverPhotoException;
import com.padr.gys.domain.realestate.port.RealEstatePhotoServicePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePhotoPersistencePort;
import com.padr.gys.infra.outbound.sftp.SFTPClientPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class RealEstatePhotoService implements RealEstatePhotoServicePort {

    @Value(value = "${sftp.storage.remoteImagesBasePath}")
    private String remoteImagesBasePath;

    private final RealEstatePhotoPersistencePort realEstatePhotoPersistencePort;

    private final SFTPClientPort storageSFTPClientPort;

    @Override
    public List<RealEstatePhoto> createAll(List<RealEstatePhoto> realEstatePhotos) {
        throwExceptionIfDuplicatedCoverPhoto(realEstatePhotos);

        realEstatePhotos.stream().forEach(realEstatePhoto -> {
            String path = String.format("%s/%s.%s",
                    remoteImagesBasePath + realEstatePhoto.getRealEstate().getId().toString(),
                    UUID.randomUUID().toString(), realEstatePhoto.getExtension());

            realEstatePhoto.setIsActive(true);
            realEstatePhoto.setPath(path);
        });

        realEstatePhotoPersistencePort.saveAll(realEstatePhotos);

        realEstatePhotos.stream().forEach(realEstatePhoto -> {
            byte[] content = Base64.getDecoder().decode(realEstatePhoto.getContentBase64());

            storageSFTPClientPort.put(realEstatePhoto.getPath(), content);
        });

        return realEstatePhotos;
    }

    @Override
    public List<RealEstatePhoto> findByRealEstateId(Long realEstateId) {
        return realEstatePhotoPersistencePort.findByRealEstateId(realEstateId);
    }

    @Override
    public void updateAll(RealEstate realEstate, List<RealEstatePhoto> realEstatePhotos) {
        throwExceptionIfDuplicatedCoverPhoto(realEstatePhotos);

        List<RealEstatePhoto> oldRealEstatePhotos = findByRealEstateId(realEstate.getId());

        oldRealEstatePhotos.stream().forEach(oldRealEstatePhoto -> {
            Optional<RealEstatePhoto> realEstatePhotoOptional = realEstatePhotos.stream()
                    .filter(r -> Objects.nonNull(r.getId()) && r.getId().equals(oldRealEstatePhoto.getId())).findAny();

            if (!realEstatePhotoOptional.isPresent()) {
                oldRealEstatePhoto.setIsActive(false);

                storageSFTPClientPort.delete(oldRealEstatePhoto.getPath());
            } else
                oldRealEstatePhoto.setIsCover(realEstatePhotoOptional.get().getIsCover());
        });

        List<RealEstatePhoto> newRealEstatePhotos = new ArrayList<>();

        realEstatePhotos.stream().forEach(realEstatePhoto -> {
            Optional<RealEstatePhoto> realEstatePhotoOptional = oldRealEstatePhotos.stream()
                    .filter(r -> r.getId().equals(realEstatePhoto.getId())).findAny();

            if (!realEstatePhotoOptional.isPresent()) {
                realEstatePhoto.setRealEstate(realEstate);

                newRealEstatePhotos.add(realEstatePhoto);
            }
        });

        createAll(newRealEstatePhotos);
        realEstatePhotoPersistencePort.saveAll(oldRealEstatePhotos);
    }

    private static void throwExceptionIfDuplicatedCoverPhoto(List<RealEstatePhoto> realEstatePhotos) {
        if (realEstatePhotos.stream().filter(r -> r.getIsCover()).count() > 1)
            throw new DuplicateRealEstateCoverPhotoException();
    }
}
