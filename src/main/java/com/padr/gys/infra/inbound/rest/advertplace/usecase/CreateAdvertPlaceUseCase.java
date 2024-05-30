package com.padr.gys.infra.inbound.rest.advertplace.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.advertplace.model.AdvertPlaceRequest;
import com.padr.gys.infra.inbound.rest.advertplace.model.AdvertPlaceResponse;
import com.padr.gys.infra.outbound.persistence.advertplace.port.AdvertPlacePersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateAdvertPlaceUseCase {

    private final AdvertPlacePersistencePort advertPlacePersistencePort;

    public AdvertPlaceResponse execute(AdvertPlaceRequest advertPlaceRequest) {
        return AdvertPlaceResponse.of(advertPlacePersistencePort.save(advertPlaceRequest.to()));
    }
}
