package com.padr.gys.infra.inbound.rest.rentalcontract.usecase;

import com.padr.gys.domain.rentalcontract.port.RentalContractServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteRentalContractUseCase {

    private final RentalContractServicePort rentalContractServicePort;

    public void execute(Long id) {
        rentalContractServicePort.delete(id);
    }
}
