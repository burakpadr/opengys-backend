package com.padr.gys.infra.inbound.rest.rentalcontract.usecase;

import com.padr.gys.domain.archive.entity.Archive;
import com.padr.gys.domain.archive.port.ArchiveServicePort;
import com.padr.gys.domain.common.property.AppProperty;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.rentalcontract.port.RentalContractServicePort;
import com.padr.gys.infra.inbound.rest.rentalcontract.model.request.UpdateRentalContractRequest;
import com.padr.gys.infra.inbound.rest.rentalcontract.model.response.RentalContractResponse;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UpdateRentalContractUseCase {

    private final RentalContractServicePort rentalContractService;
    private final ArchiveServicePort archiveServicePort;

    private final AppProperty appProperty;

    public RentalContractResponse execute(Long id, Optional<MultipartFile> rentalContractFile,
            UpdateRentalContractRequest request) {
        
        RentalContract oldRentalContract = rentalContractService.findById(id);
        RentalContract newRentalContract = request.to();

        if (rentalContractFile.isPresent()) {
            // Upload rental contract file

            Archive rentalContractFileArchive = archiveServicePort.create(rentalContractFile.get(),
                        oldRentalContract.getRealEstate().getId(), 
                        appProperty.getStorage().getRentalContractFilesPath(), 
                        appProperty.getStorage().getRentalContractFilesRelativeUrl());

            newRentalContract.setRentalContractFile(rentalContractFileArchive);
        }

        return RentalContractResponse.of(rentalContractService.update(oldRentalContract, newRentalContract));
    }

}
