package com.padr.gys.infra.inbound.rest.user.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.rentalcontract.port.RentalContractServicePort;
import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.domain.user.port.TenantServicePort;
import com.padr.gys.domain.user.port.UserServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteTenantUseCase {

    private final TenantServicePort tenantServicePort;
    private final UserServicePort userServicePort;
    private final RentalContractServicePort rentalContractServicePort;

    public void execute(Long id) {
        Tenant tenant = tenantServicePort.findById(id);
        List<RentalContract> rentalContracts = rentalContractServicePort.findByTenantId(tenant.getId());

        rentalContractServicePort.deleteAll(rentalContracts);
        userServicePort.delete(tenant.getUser().getId());
        tenantServicePort.delete(tenant);
    }
}
