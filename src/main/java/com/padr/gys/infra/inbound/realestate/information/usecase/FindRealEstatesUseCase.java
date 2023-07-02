package com.padr.gys.infra.inbound.realestate.information.usecase;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.information.entity.RealEstate;
import com.padr.gys.domain.realestate.information.port.RealEstateServicePort;
import com.padr.gys.infra.inbound.realestate.information.model.response.RealEstateResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindRealEstatesUseCase {

    private final RealEstateServicePort realEstateServicePort;

    public Page<RealEstateResponse> execute(Pageable pageable) {
        Page<RealEstate> realEstates = realEstateServicePort.findByIsActive(true, pageable);

        List<RealEstateResponse> realEstateResponses = realEstates.getContent().stream().map(RealEstateResponse::of)
                .toList();

        return new PageImpl<>(realEstateResponses, pageable, realEstates.getTotalElements());
    }
}
