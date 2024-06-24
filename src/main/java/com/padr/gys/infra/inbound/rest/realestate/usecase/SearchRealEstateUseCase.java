package com.padr.gys.infra.inbound.rest.realestate.usecase;

import com.padr.gys.infra.inbound.rest.realestate.model.response.RealEstateResponse;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchRealEstateUseCase {

    private final RealEstatePersistencePort realEstatePersistencePort;

    public Page<RealEstateResponse> execute(String searchTerm, Pageable pageable) {
        return realEstatePersistencePort.findBySearchTerm(searchTerm, pageable).map(RealEstateResponse::of);
    }
}
