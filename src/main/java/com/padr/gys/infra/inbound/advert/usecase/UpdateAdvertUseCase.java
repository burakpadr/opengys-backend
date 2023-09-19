package com.padr.gys.infra.inbound.advert.usecase;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.advert.port.AdvertServicePort;
import com.padr.gys.domain.advertplace.entity.persistence.AdvertPlace;
import com.padr.gys.domain.advertplace.port.AdvertPlaceServicePort;
import com.padr.gys.infra.inbound.advert.model.request.UpdateAdvertRequest;
import com.padr.gys.infra.inbound.advert.model.response.AdvertResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateAdvertUseCase {

    private final AdvertServicePort advertServicePort;
    private final AdvertPlaceServicePort advertPlaceServicePort;

    public AdvertResponse execute(Long id, UpdateAdvertRequest request) {
        Advert advert = request.to();
        AdvertPlace advertPlace = advertPlaceServicePort.findByIdAndIsActive(request.getAdvertPlaceId(), true);

        advert.setAdvertPlace(advertPlace);

        return AdvertResponse.of(advertServicePort.update(id, advert));
    }
}

