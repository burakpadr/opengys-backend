package com.padr.gys.infra.inbound.rest.rbac.usecase;

import com.padr.gys.infra.outbound.persistence.rbac.port.RolePersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.entity.Role;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class DeleteRoleUseCase {
    
    private final RolePersistencePort rolePersistencePort;

    private final MessageSource messageSource;

    public void execute(Long roleId) {
        Role role = rolePersistencePort.findById(roleId)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("rbac.role.not-found", null, LocaleContextHolder.getLocale())));

        role.setIsDeleted(true);

        rolePersistencePort.save(role);
    }
}
