package com.padr.gys.infra.inbound.advertplace.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.advertplace.port.AdvertPlaceServicePort;
import com.padr.gys.infra.inbound.advertplace.model.AdvertPlaceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SearchAdvertPlacesUseCase {

    private final AdvertPlaceServicePort advertPlaceServicePort;

    public Page<AdvertPlaceResponse> execute(String searchTerm, Pageable pageable) {
        return advertPlaceServicePort.search(searchTerm, pageable).map(AdvertPlaceResponse::of);
    }
}
