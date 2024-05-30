package com.padr.gys.infra.inbound.rest.user.usecase;

import com.padr.gys.domain.user.entity.User;
import com.padr.gys.infra.outbound.persistence.user.port.TenantPersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.UserPersistencePort;
import jakarta.persistence.EntityExistsException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.entity.Tenant;
import com.padr.gys.infra.inbound.rest.user.model.request.UpdateTenantRequest;
import com.padr.gys.infra.inbound.rest.user.model.response.TenantResponse;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class UpdateTenantUseCase {
    
    private final TenantPersistencePort tenantPersistencePort;
    private final UserPersistencePort userPersistencePort;

    private final MessageSource messageSource;

    public TenantResponse execute(Long id, UpdateTenantRequest request) {
        Tenant oldTenant = tenantPersistencePort.findById(id).orElseThrow(() -> new NoSuchElementException(
                messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

        User user = oldTenant.getUser();

        if (!user.getEmail().equals(request.getUser().getEmail())) {
            userPersistencePort.findByEmail(request.getUser().getEmail())
                    .ifPresent(u -> {
                        throw new EntityExistsException(
                                messageSource.getMessage("user.already-exist", null, LocaleContextHolder.getLocale()));
                    });
        }

        user.setName(request.getUser().getName());
        user.setSurname(request.getUser().getSurname());
        user.setRole(null);
        user.setEmail(request.getUser().getEmail());

        userPersistencePort.save(user);

        return TenantResponse.of(oldTenant);
    }
}
