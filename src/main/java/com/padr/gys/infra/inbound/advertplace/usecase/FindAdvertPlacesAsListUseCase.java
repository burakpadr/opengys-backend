package com.padr.gys.infra.inbound.advertplace.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.advertplace.port.AdvertPlaceServicePort;
import com.padr.gys.infra.inbound.advertplace.model.AdvertPlaceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAdvertPlacesAsListUseCase {

    private final AdvertPlaceServicePort advertPlaceServicePort;

    public List<AdvertPlaceResponse> execute() {
        return advertPlaceServicePort
                .findByIsActive(true)
                .stream()
                .map(AdvertPlaceResponse::of)
                .toList();
    }
}
