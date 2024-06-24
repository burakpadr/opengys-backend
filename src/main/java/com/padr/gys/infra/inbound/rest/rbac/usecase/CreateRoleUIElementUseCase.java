package com.padr.gys.infra.inbound.rest.rbac.usecase;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.padr.gys.infra.outbound.persistence.rbac.port.RolePersistencePort;
import com.padr.gys.infra.outbound.persistence.rbac.port.RoleUIElementPersistencePort;
import com.padr.gys.infra.outbound.persistence.rbac.port.UIElementPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.entity.Role;
import com.padr.gys.domain.rbac.entity.RoleUIElement;
import com.padr.gys.infra.inbound.rest.rbac.model.request.RoleUIElementRequest;
import com.padr.gys.infra.inbound.rest.rbac.model.response.RoleUIElementResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateRoleUIElementUseCase {

    private final RolePersistencePort rolePersistencePort;
    private final UIElementPersistencePort uiElementPersistencePort;
    private final RoleUIElementPersistencePort roleUIElementPersistencePort;

    private final MessageSource messageSource;

    public List<RoleUIElementResponse> execute(RoleUIElementRequest request) {
        final Role role = Role.builder()
                .label(request.getRoleRequest().getLabel())
                .build();

        rolePersistencePort.save(role);

        List<RoleUIElement> roleUIElements = request.getUiElementIds()
                .stream()
                .map(uiElementId -> {
                    return uiElementPersistencePort.findById(uiElementId)
                            .orElseThrow(() -> new NoSuchElementException(
                                    messageSource.getMessage("rbac.ui-element.not-found", null, LocaleContextHolder.getLocale())));
                })
                .toList()
                .stream()
                .map(uiElement -> {
                    return RoleUIElement.builder()
                            .role(role)
                            .uiElement(uiElement)
                            .build();
                }).collect(Collectors.toList());

        return roleUIElementPersistencePort.saveAll(roleUIElements)
                .stream()
                .map(RoleUIElementResponse::of)
                .toList();
    }
}
