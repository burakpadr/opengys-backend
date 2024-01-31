package com.padr.gys.infra.inbound.rest.advert.usecase;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.advert.port.AdvertServicePort;
import com.padr.gys.domain.advertplace.entity.AdvertPlace;
import com.padr.gys.domain.advertplace.port.AdvertPlaceServicePort;
import com.padr.gys.domain.common.exception.BusinessException;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.rentalcontract.port.RentalContractServicePort;
import com.padr.gys.domain.status.constant.MainStatus;
import com.padr.gys.infra.inbound.rest.advert.model.request.UpdateAdvertRequest;
import com.padr.gys.infra.inbound.rest.advert.model.response.AdvertResponse;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateAdvertUseCase {

    private final AdvertServicePort advertServicePort;
    private final AdvertPlaceServicePort advertPlaceServicePort;
    private final RentalContractServicePort rentalContractServicePort;

    private final MessageSource messageSource;

    public AdvertResponse execute(Long id, UpdateAdvertRequest request) {
        Advert oldAdvert = advertServicePort.findById(id);

        if (oldAdvert.getRealEstate().getMainStatus().equals(MainStatus.FOR_RENT) && request.getIsPublished()) {
            List<RentalContract> publishedRentalContracts = rentalContractServicePort
                    .findByRealEstateIdAndIsPublished(oldAdvert.getRealEstate().getId(), true);

            if (publishedRentalContracts.size() > 0)
                throw new BusinessException(
                        messageSource.getMessage("advert.cannot-be-published-when-rental-contract-published", null,
                                LocaleContextHolder.getLocale()));
        }

        AdvertPlace advertPlace = advertPlaceServicePort.findById(request.getAdvertPlaceId());

        Advert newAdvert = Advert.builder()
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .price(request.getPrice())
                .isPublished(request.getIsPublished())
                .advertPlace(advertPlace)
                .build();

        return AdvertResponse.of(advertServicePort.update(oldAdvert, newAdvert));
    }
}
