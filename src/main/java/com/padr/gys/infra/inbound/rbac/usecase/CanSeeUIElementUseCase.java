package com.padr.gys.infra.inbound.rbac.usecase;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.rbac.port.RoleUIElementServicePort;
import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.domain.user.entity.User;
import com.padr.gys.domain.user.port.StaffServicePort;
import com.padr.gys.infra.inbound.common.context.UserContext;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CanSeeUIElementUseCase {

    private final StaffServicePort staffServicePort;
    private final RoleUIElementServicePort roleUIElementServicePort;

    public Boolean execute(String componentName) {
        User user = UserContext.getUser();
        Boolean isStaff = UserContext.getIsStaff();

        if (Objects.nonNull(user)) {
            if (Objects.nonNull(isStaff) && isStaff) {
                Staff staff = staffServicePort.findByUserId(user.getId());

                if (staff.getIsDeedOwner())
                    return true;
                else
                    return roleUIElementServicePort.findByRoleId(user.getRole().getId())
                            .stream()
                            .anyMatch(roleUIElement -> roleUIElement.getUiElement().getComponentName()
                                    .equals(componentName));

            }
        }

        return false;
    }
}
