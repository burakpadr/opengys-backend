package com.padr.gys.infra.inbound.rest.realestate.usecase;

import java.util.List;

import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePhotoPersistencePort;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.realestate.model.response.RealEstatePhotoResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindRealEstatePhotosUseCase {

    private final RealEstatePhotoPersistencePort realEstatePhotoPersistencePort;

    public List<RealEstatePhotoResponse> execute(Long realEstateId) {
        return realEstatePhotoPersistencePort.findByRealEstateId(realEstateId)
                .stream()
                .map(RealEstatePhotoResponse::of)
                .toList();
    }
}
