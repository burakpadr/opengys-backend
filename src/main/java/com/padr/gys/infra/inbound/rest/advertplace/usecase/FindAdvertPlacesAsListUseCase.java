package com.padr.gys.infra.inbound.rest.advertplace.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.advertplace.model.AdvertPlaceResponse;
import com.padr.gys.infra.outbound.persistence.advertplace.port.AdvertPlacePersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAdvertPlacesAsListUseCase {

    private final AdvertPlacePersistencePort advertPlacePersistencePort;

    public List<AdvertPlaceResponse> execute() {
        return advertPlacePersistencePort
                .findAll()
                .stream()
                .map(AdvertPlaceResponse::of)
                .toList();
    }
}
