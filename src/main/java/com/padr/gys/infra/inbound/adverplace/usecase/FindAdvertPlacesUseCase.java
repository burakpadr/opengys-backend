package com.padr.gys.infra.inbound.adverplace.usecase;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.advertplace.entity.persistence.AdvertPlace;
import com.padr.gys.domain.advertplace.port.AdvertPlaceServicePort;
import com.padr.gys.infra.inbound.adverplace.model.AdvertPlaceResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAdvertPlacesUseCase {

    private final AdvertPlaceServicePort advertPlaceServicePort;

    public Page<AdvertPlaceResponse> execute(Pageable pageable) {
        Page<AdvertPlace> advertPlaces = advertPlaceServicePort.findByIsActive(true, pageable);

        List<AdvertPlaceResponse> advertPlaceResponses = advertPlaces.getContent().stream().map(AdvertPlaceResponse::of)
                .toList();

        return new PageImpl<>(advertPlaceResponses, pageable, advertPlaces.getTotalElements());
    }
}
