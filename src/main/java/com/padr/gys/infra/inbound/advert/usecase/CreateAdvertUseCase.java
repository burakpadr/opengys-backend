package com.padr.gys.infra.inbound.advert.usecase;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.advert.port.AdvertServicePort;
import com.padr.gys.domain.advertplace.entity.persistence.AdvertPlace;
import com.padr.gys.domain.advertplace.port.AdvertPlaceServicePort;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;
import com.padr.gys.infra.inbound.advert.model.request.CreateAdvertRequest;
import com.padr.gys.infra.inbound.advert.model.response.AdvertResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAdvertUseCase {

    private final AdvertServicePort advertServicePort;
    private final RealEstateServicePort realEstateServicePort;
    private final AdvertPlaceServicePort advertPlaceServicePort;

    public AdvertResponse execute(CreateAdvertRequest request) {
        Advert advert = request.to();
        RealEstate realEstate = realEstateServicePort.findByIdAndIsActive(request.getRealEstateId(), true);
        AdvertPlace advertPlace = advertPlaceServicePort.findByIdAndIsActive(request.getAdvertPlaceId(), true);

        advert.setRealEstate(realEstate);
        advert.setAdvertPlace(advertPlace);

        return AdvertResponse.of(advertServicePort.create(advert));
    }
}
