package com.padr.gys.infra.inbound.rest.rentalcontract.adapter;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.padr.gys.infra.inbound.rest.rentalcontract.model.request.CreateRentalContractRequest;
import com.padr.gys.infra.inbound.rest.rentalcontract.model.request.UpdateRentalContractRequest;
import com.padr.gys.infra.inbound.rest.rentalcontract.model.response.RentalContractResponse;
import com.padr.gys.infra.inbound.rest.rentalcontract.usecase.*;

@RestController
@RequestMapping("/gys/api/v1/rental-contracts")
@RequiredArgsConstructor
public class RentalContractAdapter {

    private final CreateRentalContractUseCase createRentalContractUseCase;
    private final FindRentalContractUseCase findRentalContractUseCase;
    private final FindRentalContractByIdUseCase findRentalContractByIdUseCase;
    private final UpdateRentalContractUseCase updateRentalContractUseCase;
    private final DeleteRentalContractUseCase deleteRentalContractUseCase;

    @GetMapping
    public Page<RentalContractResponse> find(@RequestParam Long realEstateId, Pageable pageable) {
        return findRentalContractUseCase.execute(realEstateId, pageable);
    }

    @PostMapping
    public RentalContractResponse create(@RequestPart Optional<MultipartFile> rentalContractFile,
            @Valid CreateRentalContractRequest request) {
        return createRentalContractUseCase.execute(rentalContractFile, request);
    }

    @GetMapping("/{rentalContractId}")
    public RentalContractResponse findById(@PathVariable Long rentalContractId) {
        return findRentalContractByIdUseCase.execute(rentalContractId);
    }

    @PutMapping("/{rentalContractId}")
    public RentalContractResponse update(@PathVariable Long rentalContractId,
            @RequestPart Optional<MultipartFile> rentalContractFile,
            @Valid UpdateRentalContractRequest request) {
        return updateRentalContractUseCase.execute(rentalContractId, rentalContractFile,request);
    }

    @DeleteMapping("/{rentalContractId}")
    public void delete(@PathVariable long rentalContractId) {
        deleteRentalContractUseCase.execute(rentalContractId);
    }
}
