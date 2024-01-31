package com.padr.gys.infra.inbound.rest.user.usecase;

import java.util.Objects;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.common.exception.BusinessException;
import com.padr.gys.domain.rbac.entity.Role;
import com.padr.gys.domain.rbac.port.RoleServicePort;
import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.domain.user.port.StaffServicePort;
import com.padr.gys.domain.user.port.UserServicePort;
import com.padr.gys.infra.inbound.rest.user.model.request.UpdateStaffRequest;
import com.padr.gys.infra.inbound.rest.user.model.response.StaffResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateStaffUseCase {

    private final UserServicePort userServicePort;
    private final StaffServicePort staffServicePort;
    private final RoleServicePort roleServicePort;

    private final MessageSource messageSource;

    public StaffResponse execute(Long id, UpdateStaffRequest request) {
        Staff oldStaff = staffServicePort.findById(id);

        Role role = null;

        if (!request.getIsDeedOwner()) {
            if (Objects.isNull(request.getUser().getRoleId()))
                throw new BusinessException(messageSource.getMessage("user.role-id-cannot-be-empty", null,
                        LocaleContextHolder.getLocale()));

            role = roleServicePort.findById(request.getUser().getRoleId());
        }

        userServicePort.update(oldStaff.getUser(), request.getUser().to(role));
        staffServicePort.update(oldStaff, request.to(oldStaff.getUser()));

        return StaffResponse.of(oldStaff);
    }
}
