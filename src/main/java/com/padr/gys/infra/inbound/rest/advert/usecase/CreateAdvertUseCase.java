package com.padr.gys.infra.inbound.rest.advert.usecase;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.advertplace.entity.AdvertPlace;
import com.padr.gys.domain.common.exception.BusinessException;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.status.constant.MainStatus;
import com.padr.gys.infra.inbound.rest.advert.model.request.CreateAdvertRequest;
import com.padr.gys.infra.inbound.rest.advert.model.response.AdvertResponse;
import com.padr.gys.infra.outbound.persistence.advert.port.AdvertPersistencePort;
import com.padr.gys.infra.outbound.persistence.advertplace.port.AdvertPlacePersistencePort;

import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import com.padr.gys.infra.outbound.persistence.rentalcontract.port.RentalContractPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAdvertUseCase {

    private final AdvertPersistencePort advertPersistencePort;
    private final AdvertPlacePersistencePort advertPlacePersistencePort;
    private final RealEstatePersistencePort realEstatePersistencePort;
    private final RentalContractPersistencePort rentalContractPersistencePort;

    private final MessageSource messageSource;

    public AdvertResponse execute(CreateAdvertRequest request) {
        RealEstate realEstate = realEstatePersistencePort.findById(request.getRealEstateId())
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("realestate.not-found", null, LocaleContextHolder.getLocale())));

        AdvertPlace advertPlace = advertPlacePersistencePort.findById(request.getAdvertPlaceId())
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("advertplace.not-found", null, LocaleContextHolder.getLocale())));

        if (realEstate.getMainStatus().equals(MainStatus.FOR_RENT) && request.getIsPublished()) {
            List<RentalContract> publishedRentalContracts = rentalContractPersistencePort
                    .findByRealEstateIdAndIsPublished(realEstate.getId(), true);

            if (publishedRentalContracts.size() > 0)
                throw new BusinessException(
                        messageSource.getMessage("advert.cannot-be-published-when-rental-contract-published", null,
                                LocaleContextHolder.getLocale()));
        }

        Advert advert = Advert.builder()
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .price(request.getPrice())
                .isPublished(request.getIsPublished())
                .realEstate(realEstate)
                .advertPlace(advertPlace)
                .build();

        return AdvertResponse.of(advertPersistencePort.save(advert));
    }
}
