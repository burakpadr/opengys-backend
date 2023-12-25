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
public class CreateRoleUIElementUseCase {

    private final RoleServicePort roleServicePort;
    private final UIElementServicePort uiElementServicePort;
    private final RoleUIElementServicePort roleUIElementServicePort;

    public List<RoleUIElementResponse> execute(RoleUIElementRequest request) {
        final Role role = Role.builder()
                .label(request.getRoleRequest().getLabel())
                .build();

        roleServicePort.save(role);

        List<RoleUIElement> roleUIElements = request.getUiElementIds()
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

        return roleUIElementServicePort.saveAll(roleUIElements)
                .stream()
                .map(RoleUIElementResponse::of)
                .toList();
    }
}
