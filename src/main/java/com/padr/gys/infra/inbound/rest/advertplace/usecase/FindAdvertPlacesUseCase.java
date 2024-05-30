package com.padr.gys.infra.inbound.rest.advertplace.usecase;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.advertplace.entity.AdvertPlace;
import com.padr.gys.infra.inbound.rest.advertplace.model.AdvertPlaceResponse;
import com.padr.gys.infra.outbound.persistence.advertplace.port.AdvertPlacePersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAdvertPlacesUseCase {

    private final AdvertPlacePersistencePort advertPlacePersistencePort;

    public Page<AdvertPlaceResponse> execute(Pageable pageable) {
        Page<AdvertPlace> advertPlaces = advertPlacePersistencePort.findAll(pageable);

        List<AdvertPlaceResponse> advertPlaceResponses = advertPlaces.getContent().stream().map(AdvertPlaceResponse::of)
                .toList();

        return new PageImpl<>(advertPlaceResponses, pageable, advertPlaces.getTotalElements());
    }
}
