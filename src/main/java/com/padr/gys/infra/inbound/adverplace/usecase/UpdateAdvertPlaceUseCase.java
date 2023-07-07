package com.padr.gys.infra.inbound.adverplace.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.advertplace.port.AdvertPlaceServicePort;
import com.padr.gys.infra.inbound.adverplace.model.AdvertPlaceRequest;
import com.padr.gys.infra.inbound.adverplace.model.AdvertPlaceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateAdvertPlaceUseCase {

    private final AdvertPlaceServicePort advertPlaceServicePort;

    public AdvertPlaceResponse execute(Long id, AdvertPlaceRequest request) {
        return AdvertPlaceResponse.of(advertPlaceServicePort.update(id, request.to()));
    }
}
