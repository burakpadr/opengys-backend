package com.padr.gys.infra.inbound.rest.realestate.usecase;

import java.util.List;

import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.infra.inbound.rest.realestate.model.response.RealEstateResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindRealEstatesUseCase {

    private final RealEstatePersistencePort realEstatePersistencePort;

    public Page<RealEstateResponse> execute(Pageable pageable) {
        Page<RealEstate> realEstates = realEstatePersistencePort.findAll(pageable);

        List<RealEstateResponse> realEstateResponses = realEstates.getContent().stream().map(RealEstateResponse::of)
                .toList();

        return new PageImpl<>(realEstateResponses, pageable, realEstates.getTotalElements());
    }
}
