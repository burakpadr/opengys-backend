package com.padr.gys.infra.inbound.rest.rbac.usecase;

import com.padr.gys.domain.rbac.entity.Role;
import com.padr.gys.infra.outbound.persistence.rbac.port.RolePersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.rbac.model.response.RoleResponse;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class FindRoleByIdUseCase {
    
    private final RolePersistencePort rolePersistencePort;

    private final MessageSource messageSource;

    public RoleResponse execute(Long id) {
        Role role = rolePersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("rbac.role.not-found", null, LocaleContextHolder.getLocale())));

        return RoleResponse.of(role);
    }
}
