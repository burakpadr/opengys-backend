package com.padr.gys.infra.inbound.rest.rbac.usecase;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.padr.gys.infra.outbound.persistence.rbac.port.RoleUIElementPersistencePort;
import com.padr.gys.infra.outbound.persistence.rbac.port.UIElementPersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.StaffPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.domain.user.entity.User;
import com.padr.gys.infra.inbound.common.context.UserContext;
import com.padr.gys.infra.inbound.rest.rbac.model.response.UIElementResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllowedComponentsToBeSeenUseCase {

    private final RoleUIElementPersistencePort roleUIElementPersistencePort;
    private final UIElementPersistencePort uiElementPersistencePort;
    private final StaffPersistencePort staffPersistencePort;

    private final MessageSource messageSource;

    public List<UIElementResponse> execute() {
        User user = UserContext.getUser();
        Boolean isStaff = UserContext.getIsStaff();

        if (Objects.nonNull(user)) {
            if (Objects.nonNull(isStaff) && isStaff) {
                Staff staff = staffPersistencePort.findByUserId(user.getId())
                        .orElseThrow(() -> new NoSuchElementException(
                                messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

                if (staff.getIsDeedOwner())
                    return uiElementPersistencePort.findAll().stream().map(UIElementResponse::of).toList();
                else
                    return roleUIElementPersistencePort
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