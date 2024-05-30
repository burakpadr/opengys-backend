package com.padr.gys.infra.inbound.rest.user.usecase;

import java.util.List;
import java.util.NoSuchElementException;

import com.padr.gys.infra.outbound.persistence.rentalcontract.port.RentalContractPersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.TenantPersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.UserPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.user.entity.Tenant;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteTenantUseCase {

    private final TenantPersistencePort tenantPersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final RentalContractPersistencePort rentalContractPersistencePort;

    private final MessageSource messageSource;

    public void execute(Long id) {
        Tenant tenant = tenantPersistencePort.findById(id).orElseThrow(() -> new NoSuchElementException(
                messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

        List<RentalContract> rentalContracts = rentalContractPersistencePort.findByTenantId(tenant.getId());

        deleteRentalContractAssociatedWithTenant(rentalContracts);

        // Delete User

        tenant.getUser().setIsDeleted(true);

        userPersistencePort.save(tenant.getUser());

        // Delete tenant

        tenant.setIsDeleted(true);

        tenantPersistencePort.save(tenant);
    }

    public void deleteRentalContractAssociatedWithTenant(List<RentalContract> rentalContracts) {
        rentalContracts.forEach(rentalContract -> {
            rentalContract.setIsDeleted(true);
        });

        rentalContractPersistencePort.saveAll(rentalContracts);
    }
}
