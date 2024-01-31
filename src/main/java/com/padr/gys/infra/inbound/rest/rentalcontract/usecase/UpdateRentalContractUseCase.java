package com.padr.gys.infra.inbound.rest.rentalcontract.usecase;

import com.padr.gys.domain.rentalcontract.port.RentalContractServicePort;
import com.padr.gys.infra.inbound.rest.rentalcontract.model.request.UpdateRentalContractRequest;
import com.padr.gys.infra.inbound.rest.rentalcontract.model.response.RentalContractResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateRentalContractUseCase {

    private final RentalContractServicePort rentalContractService;

    public RentalContractResponse execute(Long id, UpdateRentalContractRequest request) {
        return RentalContractResponse.of(rentalContractService.update(id, request.to()));
    }
}
