package com.padr.gys.infra.inbound.rentalcontract.adapter;

import com.padr.gys.infra.inbound.rentalcontract.model.request.CreateRentalContractRequest;
import com.padr.gys.infra.inbound.rentalcontract.model.request.UpdateRentalContractRequest;
import com.padr.gys.infra.inbound.rentalcontract.model.response.RentalContractResponse;
import com.padr.gys.infra.inbound.rentalcontract.usecase.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rental-contracts")
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
    public RentalContractResponse create(@Valid @RequestBody CreateRentalContractRequest request) {
        return createRentalContractUseCase.execute(request);
    }

    @GetMapping("/{rentalContractId}")
    public RentalContractResponse findById(@PathVariable Long rentalContractId) {
        return findRentalContractByIdUseCase.execute(rentalContractId);
    }

    @PutMapping("/{rentalContractId}")
    public RentalContractResponse update(@PathVariable Long rentalContractId,
                                         @Valid @RequestBody UpdateRentalContractRequest request) {
        return updateRentalContractUseCase.execute(rentalContractId, request);
    }

    @DeleteMapping("/{rentalContractId}")
    public void delete(@PathVariable long rentalContractId) {
        deleteRentalContractUseCase.execute(rentalContractId);
    }
}
