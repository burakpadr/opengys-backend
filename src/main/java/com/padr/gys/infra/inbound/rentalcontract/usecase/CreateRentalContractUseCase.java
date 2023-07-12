package com.padr.gys.infra.inbound.rentalcontract.usecase;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.rentalcontract.port.RentalContractServicePort;
import com.padr.gys.infra.inbound.rentalcontract.model.request.CreateRentalContractRequest;
import com.padr.gys.infra.inbound.rentalcontract.model.response.RentalContractResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateRentalContractUseCase {

    private final RentalContractServicePort rentalContractServicePort;
    private final RealEstateServicePort realEstateServicePort;

    public RentalContractResponse execute(CreateRentalContractRequest request) {
        RentalContract rentalContract = request.to();
        RealEstate realEstate = realEstateServicePort.findByIdAndIsActive(request.getRealEstateId(), true);

        rentalContract.setRealEstate(realEstate);

        return RentalContractResponse.of(rentalContractServicePort.create(rentalContract));
    }
}
