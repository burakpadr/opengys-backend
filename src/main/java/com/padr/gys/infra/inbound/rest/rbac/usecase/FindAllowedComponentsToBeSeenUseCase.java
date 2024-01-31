package com.padr.gys.infra.inbound.rest.rbac.usecase;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.port.RoleUIElementServicePort;
import com.padr.gys.domain.rbac.port.UIElementServicePort;
import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.domain.user.entity.User;
import com.padr.gys.domain.user.port.StaffServicePort;
import com.padr.gys.infra.inbound.common.context.UserContext;
import com.padr.gys.infra.inbound.rest.rbac.model.response.UIElementResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllowedComponentsToBeSeenUseCase {

    private final StaffServicePort staffServicePort;
    private final RoleUIElementServicePort roleUIElementServicePort;
    private final UIElementServicePort uiElementServicePort;

    public List<UIElementResponse> execute() {
        User user = UserContext.getUser();
        Boolean isStaff = UserContext.getIsStaff();

        if (Objects.nonNull(user)) {
            if (Objects.nonNull(isStaff) && isStaff) {
                Staff staff = staffServicePort.findByUserId(user.getId());

                if (staff.getIsDeedOwner())
                    return uiElementServicePort.findAll().stream().map(UIElementResponse::of).toList();
                else
                    return roleUIElementServicePort
                            .findByRoleId(user.getRole().getId())
                            .stream()
                            .map(roleUIElement -> {
                                return UIElementResponse.of(roleUIElement.getUiElement());
                            }).toList();
            }
        }

        return List.of();
    }
}