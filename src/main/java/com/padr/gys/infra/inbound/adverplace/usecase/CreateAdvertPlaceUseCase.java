package com.padr.gys.infra.inbound.adverplace.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.advertplace.port.AdvertPlaceServicePort;
import com.padr.gys.infra.inbound.adverplace.model.AdvertPlaceRequest;
import com.padr.gys.infra.inbound.adverplace.model.AdvertPlaceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateAdvertPlaceUseCase {

    private final AdvertPlaceServicePort advertPlaceServicePort;

    public AdvertPlaceResponse execute(AdvertPlaceRequest advertPlaceRequest) {
        return AdvertPlaceResponse.of(advertPlaceServicePort.create(advertPlaceRequest.to()));
    }
}
