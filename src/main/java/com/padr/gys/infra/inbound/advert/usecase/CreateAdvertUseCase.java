package com.padr.gys.infra.inbound.advert.usecase;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.advert.port.AdvertServicePort;
import com.padr.gys.domain.advertplace.entity.AdvertPlace;
import com.padr.gys.domain.advertplace.port.AdvertPlaceServicePort;
import com.padr.gys.domain.common.exception.BusinessException;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.rentalcontract.port.RentalContractServicePort;
import com.padr.gys.domain.status.constant.MainStatus;
import com.padr.gys.infra.inbound.advert.model.request.CreateAdvertRequest;
import com.padr.gys.infra.inbound.advert.model.response.AdvertResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAdvertUseCase {

    private final AdvertServicePort advertServicePort;
    private final RealEstateServicePort realEstateServicePort;
    private final AdvertPlaceServicePort advertPlaceServicePort;
    private final RentalContractServicePort rentalContractServicePort;

    private final MessageSource messageSource;

    public AdvertResponse execute(CreateAdvertRequest request) {
        RealEstate realEstate = realEstateServicePort.findById(request.getRealEstateId());
        AdvertPlace advertPlace = advertPlaceServicePort.findById(request.getAdvertPlaceId());

        if (realEstate.getMainStatus().equals(MainStatus.FOR_RENT) && request.getIsPublished()) {
            List<RentalContract> publishedRentalContracts = rentalContractServicePort
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

        return AdvertResponse.of(advertServicePort.create(advert));
    }
}
