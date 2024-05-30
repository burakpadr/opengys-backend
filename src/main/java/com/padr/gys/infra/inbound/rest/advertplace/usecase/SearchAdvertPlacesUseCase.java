package com.padr.gys.infra.inbound.rest.advertplace.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.advertplace.model.AdvertPlaceResponse;
import com.padr.gys.infra.outbound.persistence.advertplace.port.AdvertPlacePersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SearchAdvertPlacesUseCase {

    private final AdvertPlacePersistencePort advertPlacePersistencePort;

    public Page<AdvertPlaceResponse> execute(String searchTerm, Pageable pageable) {
        return advertPlacePersistencePort.findBySearchTerm(searchTerm, pageable).map(AdvertPlaceResponse::of);
    }
}
