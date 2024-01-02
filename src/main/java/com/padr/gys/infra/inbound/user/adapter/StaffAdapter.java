package com.padr.gys.infra.inbound.user.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.user.model.request.StaffRequest;
import com.padr.gys.infra.inbound.user.model.response.StaffResponse;
import com.padr.gys.infra.inbound.user.usecase.CountDeedOwnerUseCase;
import com.padr.gys.infra.inbound.user.usecase.CreateStaffUseCase;
import com.padr.gys.infra.inbound.user.usecase.DeleteStaffUseCase;
import com.padr.gys.infra.inbound.user.usecase.FindAllStaffAsPageUseCase;
import com.padr.gys.infra.inbound.user.usecase.FindStaffByIdUseCase;
import com.padr.gys.infra.inbound.user.usecase.UpdateStaffUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/staffs")
@RequiredArgsConstructor
public class StaffAdapter {
    
    private final FindAllStaffAsPageUseCase findAllStaffAsPageUseCase;
    private final FindStaffByIdUseCase findStaffByIdUseCase;
    private final CountDeedOwnerUseCase countDeedOwnerUseCase;
    private final CreateStaffUseCase createStaffUseCase;
    private final UpdateStaffUseCase updateStaffUseCase;
    private final DeleteStaffUseCase deleteStaffUseCase;

    @GetMapping
    public Page<StaffResponse> findAll(Pageable pageable) {
        return findAllStaffAsPageUseCase.execute(pageable);
    }

    @GetMapping("/{id}")
    public StaffResponse findById(@PathVariable Long id) {
        return findStaffByIdUseCase.execute(id);
    }

    @GetMapping("/count-deed-owner")
    public Long countDeedOwner() {
        return countDeedOwnerUseCase.execute();
    }

    @PostMapping
    public StaffResponse create(@Validated @RequestBody StaffRequest request) {
        return createStaffUseCase.execute(request);
    }

    @PutMapping("/{id}")
    public StaffResponse update(@PathVariable Long id, @Validated @RequestBody StaffRequest request) {
        return updateStaffUseCase.execute(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteStaffUseCase.execute(id);
    }
}
