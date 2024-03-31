package com.padr.gys.infra.inbound.rest.rentalcontract.usecase;

import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.archive.port.ArchiveServicePort;
import com.padr.gys.domain.common.property.AppProperty;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.rentalcontract.port.RentalContractServicePort;
import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.domain.user.port.TenantServicePort;
import com.padr.gys.infra.inbound.rest.rentalcontract.model.request.CreateRentalContractRequest;
import com.padr.gys.infra.inbound.rest.rentalcontract.model.response.RentalContractResponse;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class CreateRentalContractUseCase {

    private final RentalContractServicePort rentalContractServicePort;
    private final RealEstateServicePort realEstateServicePort;
    private final ArchiveServicePort archiveServicePort;
    private final TenantServicePort tenantServicePort;

    private final AppProperty appProperty;

    public RentalContractResponse execute(Optional<MultipartFile> rentalContractFile,
            CreateRentalContractRequest request) {
        RentalContract rentalContract = request.to();
        RealEstate realEstate = realEstateServicePort.findById(request.getRealEstateId());
        Tenant tenant = tenantServicePort.findById(request.getTenantId());

        // Upload rental contract file

        if (rentalContractFile.isPresent()) {
            Archive rentalContractFileArchive = archiveServicePort.create(rentalContractFile.get(), realEstate.getId(),
                    appProperty.getStorage().getRentalContractFilesPath(),
                    appProperty.getStorage().getRentalContractFilesRelativeUrl());

            rentalContract.setRentalContractFile(rentalContractFileArchive);
        }

        // Create rental contract record

        rentalContract.setRealEstate(realEstate);
        rentalContract.setTenant(tenant);
        rentalContractServicePort.create(rentalContract);

        return RentalContractResponse.of(rentalContract);
    }
}
