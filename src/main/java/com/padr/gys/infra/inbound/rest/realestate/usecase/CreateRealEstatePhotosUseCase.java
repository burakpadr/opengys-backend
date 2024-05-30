package com.padr.gys.infra.inbound.rest.realestate.usecase;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePhotoPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.entity.RealEstatePhoto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateRealEstatePhotosUseCase {

    private final RealEstatePersistencePort realEstatePersistencePort;
    private final RealEstatePhotoPersistencePort realEstatePhotoPersistencePort;

    private final MessageSource messageSource;

    public void execute(Long realEstateId, List<MultipartFile> images) {
        RealEstate realEstate = realEstatePersistencePort.findById(realEstateId)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("realestate.not-found", null, LocaleContextHolder.getLocale())));

        List<RealEstatePhoto> realEstatePhotos = images.stream().map(image -> {
            return RealEstatePhoto.builder()
                    .realEstate(realEstate)
                    .image(image)
                    .build();
        }).collect(Collectors.toList());

        realEstatePhotoPersistencePort.saveAll(realEstatePhotos);
    }
}
