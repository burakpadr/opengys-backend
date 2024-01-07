package com.padr.gys.infra.inbound.rbac.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.entity.Role;
import com.padr.gys.domain.rbac.entity.RoleUIElement;
import com.padr.gys.domain.rbac.port.RoleServicePort;
import com.padr.gys.domain.rbac.port.RoleUIElementServicePort;
import com.padr.gys.domain.rbac.port.UIElementServicePort;
import com.padr.gys.infra.inbound.rbac.model.request.RoleUIElementRequest;
import com.padr.gys.infra.inbound.rbac.model.response.RoleUIElementResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateRoleUIElementUseCase {

    private final RoleServicePort roleServicePort;
    private final UIElementServicePort uiElementServicePort;
    private final RoleUIElementServicePort roleUIElementServicePort;

    public List<RoleUIElementResponse> execute(Long roleId, RoleUIElementRequest request) {
        Role role = roleServicePort.findById(roleId);

        roleServicePort.update(role, request.getRoleRequest().to());

        List<RoleUIElement> oldRoleUIElements = roleUIElementServicePort.findByRoleId(roleId);

        List<RoleUIElement> removedRoleUIElements = oldRoleUIElements.stream()
                .filter(oldRoleUIElement -> !request.getUiElementIds()
                        .contains(oldRoleUIElement.getUiElement().getId()))
                .toList();

        roleUIElementServicePort.deleteAll(removedRoleUIElements);

        List<Long> newUIElementIds = request.getUiElementIds().stream()
                .filter(uiElementId -> oldRoleUIElements.stream()
                        .noneMatch(oldRoleUIElement -> oldRoleUIElement.getUiElement().getId().equals(uiElementId)))
                .toList();

        List<RoleUIElement> newRoleUIElements = newUIElementIds
                .stream()
                .map(uiElementId -> {
                    return uiElementServicePort.findById(uiElementId);
                })
                .toList()
                .stream()
                .map(uiElement -> {
                    return RoleUIElement.builder()
                            .role(role)
                            .uiElement(uiElement)
                            .build();
                }).collect(Collectors.toList());

        return roleUIElementServicePort.saveAll(newRoleUIElements).stream().map(RoleUIElementResponse::of).toList();
    }
}
