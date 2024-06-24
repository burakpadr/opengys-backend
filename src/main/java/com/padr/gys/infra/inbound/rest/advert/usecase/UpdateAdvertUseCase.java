package com.padr.gys.infra.inbound.rest.advert.usecase;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.advertplace.entity.AdvertPlace;
import com.padr.gys.domain.common.exception.BusinessException;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.status.constant.MainStatus;
import com.padr.gys.infra.inbound.rest.advert.model.request.UpdateAdvertRequest;
import com.padr.gys.infra.inbound.rest.advert.model.response.AdvertResponse;
import com.padr.gys.infra.outbound.persistence.advert.port.AdvertPersistencePort;
import com.padr.gys.infra.outbound.persistence.advertplace.port.AdvertPlacePersistencePort;

import com.padr.gys.infra.outbound.persistence.rentalcontract.port.RentalContractPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateAdvertUseCase {

    private final AdvertPersistencePort advertPersistencePort;
    private final AdvertPlacePersistencePort advertPlacePersistencePort;
    private final RentalContractPersistencePort rentalContractPersistencePort;

    private final MessageSource messageSource;

    public AdvertResponse execute(Long id, UpdateAdvertRequest request) {
        Advert advert = advertPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("advert.not-found", null, LocaleContextHolder.getLocale())));

        if (advert.getRealEstate().getMainStatus().equals(MainStatus.FOR_RENT) && request.getIsPublished()) {
            List<RentalContract> publishedRentalContracts = rentalContractPersistencePort
                    .findByRealEstateIdAndIsPublished(advert.getRealEstate().getId(), true);

            if (publishedRentalContracts.size() > 0)
                throw new BusinessException(
                        messageSource.getMessage("advert.cannot-be-published-when-rental-contract-published", null,
                                LocaleContextHolder.getLocale()));
        }

        AdvertPlace advertPlace = advertPlacePersistencePort.findById(request.getAdvertPlaceId())
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("advertplace.not-found", null, LocaleContextHolder.getLocale())));

        advert.setAdvertPlace(advertPlace);
        advert.setStartDate(request.getStartDate());
        advert.setEndDate(request.getEndDate());
        advert.setPrice(request.getPrice());
        advert.setIsPublished(request.getIsPublished());

        return AdvertResponse.of(advertPersistencePort.save(advert));
    }
}
