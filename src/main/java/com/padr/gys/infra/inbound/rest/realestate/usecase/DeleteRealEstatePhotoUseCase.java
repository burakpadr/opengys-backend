package com.padr.gys.infra.inbound.rest.realestate.usecase;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.entity.RealEstatePhoto;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePhotoPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class DeleteRealEstatePhotoUseCase {
    
    private final RealEstatePersistencePort realEstatePersistencePort;
    private final RealEstatePhotoPersistencePort realEstatePhotoPersistencePort;

    private final MessageSource messageSource;

    public void execute(Long realEstateId, Long realEstatePhotoId) {
        // Delete real estate photo

        RealEstatePhoto realEstatePhoto = realEstatePhotoPersistencePort.findById(realEstatePhotoId)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("realestate.photo.not-found", null, LocaleContextHolder.getLocale())));

        realEstatePhoto.setIsDeleted(true);

        realEstatePhotoPersistencePort.saveAll(List.of(realEstatePhoto));

        // Remove cover photo

        RealEstate realEstate = realEstatePersistencePort.findById(realEstateId)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("realestate.not-found", null, LocaleContextHolder.getLocale())));

        realEstate.setCoverPhoto(null);

        realEstatePersistencePort.save(realEstate);
    }
}
