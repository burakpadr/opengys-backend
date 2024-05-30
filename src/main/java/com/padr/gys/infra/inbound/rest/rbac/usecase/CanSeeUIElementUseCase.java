package com.padr.gys.infra.inbound.rest.rbac.usecase;

import com.padr.gys.infra.outbound.persistence.rbac.port.RoleUIElementPersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.StaffPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.domain.user.entity.User;
import com.padr.gys.infra.inbound.common.context.UserContext;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class CanSeeUIElementUseCase {

    private final RoleUIElementPersistencePort roleUIElementPersistencePort;
    private final StaffPersistencePort staffPersistencePort;

    private final MessageSource messageSource;

    public Boolean execute(String componentName) {
        User user = UserContext.getUser();

        if (UserContext.getIsStaff()) {
            Staff staff = staffPersistencePort.findByUserId(user.getId())
                    .orElseThrow(() -> new NoSuchElementException(
                            messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

            if (staff.getIsDeedOwner())
                return true;
            else
                return roleUIElementPersistencePort.findByRoleId(user.getRole().getId())
                        .stream()
                        .anyMatch(roleUIElement -> roleUIElement.getUiElement().getComponentName()
                                .equals(componentName));

        }

        return false;
    }
}
