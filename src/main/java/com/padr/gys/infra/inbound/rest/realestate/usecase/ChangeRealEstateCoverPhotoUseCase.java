package com.padr.gys.infra.inbound.rest.realestate.usecase;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePhotoPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.entity.RealEstatePhoto;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class ChangeRealEstateCoverPhotoUseCase {

    private final RealEstatePersistencePort realEstatePersistencePort;
    private final RealEstatePhotoPersistencePort realEstatePhotoPersistencePort;

    private final MessageSource messageSource;

    public void execute(Long realEstateId, Long realEstatePhotoId) {
        RealEstatePhoto coverPhoto = realEstatePhotoPersistencePort.findById(realEstatePhotoId)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("realestate.photo.not-found", null, LocaleContextHolder.getLocale())));

        RealEstate realEstate = realEstatePersistencePort.findById(realEstateId)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("realestate.not-found", null, LocaleContextHolder.getLocale())));

        realEstate.setCoverPhoto(coverPhoto);

        realEstatePersistencePort.save(realEstate);
    }
}
