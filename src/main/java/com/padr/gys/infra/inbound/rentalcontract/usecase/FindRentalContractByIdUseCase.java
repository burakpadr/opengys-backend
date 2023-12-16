package com.padr.gys.infra.inbound.rentalcontract.usecase;

import com.padr.gys.domain.rentalcontract.port.RentalContractServicePort;
import com.padr.gys.infra.inbound.rentalcontract.model.response.RentalContractResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindRentalContractByIdUseCase {

    private final RentalContractServicePort rentalContractServicePort;

    public RentalContractResponse execute(Long id) {
        return RentalContractResponse.of(rentalContractServicePort.findById(id));
    }
}
