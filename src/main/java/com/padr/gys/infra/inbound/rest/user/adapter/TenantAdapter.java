package com.padr.gys.infra.inbound.rest.user.adapter;

import java.util.List;

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

import com.padr.gys.infra.inbound.rest.user.model.request.CreateTenantRequest;
import com.padr.gys.infra.inbound.rest.user.model.request.UpdateTenantRequest;
import com.padr.gys.infra.inbound.rest.user.model.response.TenantResponse;
import com.padr.gys.infra.inbound.rest.user.usecase.CreateTenantUseCase;
import com.padr.gys.infra.inbound.rest.user.usecase.DeleteTenantUseCase;
import com.padr.gys.infra.inbound.rest.user.usecase.FindTenantByIdUseCase;
import com.padr.gys.infra.inbound.rest.user.usecase.FindTenantsAsPageUseCase;
import com.padr.gys.infra.inbound.rest.user.usecase.FindTenantsWithoutRentalContractUseCase;
import com.padr.gys.infra.inbound.rest.user.usecase.UpdateTenantUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/tenants")
@RequiredArgsConstructor
public class TenantAdapter {

    private final CreateTenantUseCase createTenantUseCase;
    private final FindTenantsAsPageUseCase findTenantsAsPageUseCase;
    private final FindTenantsWithoutRentalContractUseCase findTenantsWithoutRentalContractUseCase;
    private final FindTenantByIdUseCase findTenantByIdUseCase;
    private final DeleteTenantUseCase deleteTenantUseCase;
    private final UpdateTenantUseCase updateTenantUseCase;

    @PostMapping
    public TenantResponse create(@Validated @RequestBody CreateTenantRequest request) {
        return createTenantUseCase.execute(request);
    }

    @GetMapping("/as-page")
    public Page<TenantResponse> findAll(Pageable pageable) {
        return findTenantsAsPageUseCase.execute(pageable);
    }

    @GetMapping("/does-not-have-contract")
    public List<TenantResponse> findByRentalContractIsNull() {
        return findTenantsWithoutRentalContractUseCase.execute();
    }

    @GetMapping("/{id}")
    public TenantResponse findById(@PathVariable Long id) {
        return findTenantByIdUseCase.execute(id);
    }

    @PutMapping("/{id}")
    public TenantResponse update(@PathVariable Long id, @Validated @RequestBody UpdateTenantRequest request) {
        return updateTenantUseCase.execute(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteTenantUseCase.execute(id);
    }
}
