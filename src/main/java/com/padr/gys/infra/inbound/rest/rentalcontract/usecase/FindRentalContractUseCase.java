package com.padr.gys.infra.inbound.rest.rentalcontract.usecase;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.infra.inbound.rest.rentalcontract.model.response.RentalContractResponse;

import com.padr.gys.infra.outbound.persistence.rentalcontract.port.RentalContractPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindRentalContractUseCase {

    private final RentalContractPersistencePort rentalContractPersistencePort;

    public Page<RentalContractResponse> execute(Long realEstateId, Pageable pageable) {
        Page<RentalContract> rentalContracts = rentalContractPersistencePort.findByRealEstateId(realEstateId, pageable);

        List<RentalContractResponse> rentalContractResponses = rentalContracts.getContent()
                .stream()
                .map(RentalContractResponse::of)
                .toList();

        return new PageImpl<>(rentalContractResponses, pageable, rentalContracts.getTotalElements());
    }
}
