package com.padr.gys.infra.inbound.rentalcontract.usecase;

import com.padr.gys.domain.rentalcontract.service.RentalContractService;
import com.padr.gys.infra.inbound.rentalcontract.model.request.UpdateRentalContractRequest;
import com.padr.gys.infra.inbound.rentalcontract.model.response.RentalContractResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateRentalContractUseCase {

    private final RentalContractService rentalContractService;

    public RentalContractResponse execute(Long id, UpdateRentalContractRequest request) {
        return RentalContractResponse.of(rentalContractService.update(id, request.to()));
    }
}
