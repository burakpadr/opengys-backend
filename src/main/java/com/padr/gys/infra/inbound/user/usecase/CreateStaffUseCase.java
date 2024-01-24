package com.padr.gys.infra.inbound.user.usecase;

import java.util.Objects;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.common.exception.BusinessException;
import com.padr.gys.domain.rbac.entity.Role;
import com.padr.gys.domain.rbac.port.RoleServicePort;
import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.domain.user.entity.User;
import com.padr.gys.domain.user.port.StaffServicePort;
import com.padr.gys.domain.user.port.UserServicePort;
import com.padr.gys.infra.inbound.user.model.request.CreateStaffRequest;
import com.padr.gys.infra.inbound.user.model.response.StaffResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateStaffUseCase {

    private final UserServicePort userServicePort;
    private final StaffServicePort staffServicePort;
    private final RoleServicePort roleServicePort;

    private final MessageSource messageSource;

    public StaffResponse execute(CreateStaffRequest request) {
        Role role = null;

        if (!request.getIsDeedOwner()) {
            if (Objects.isNull(request.getUser().getRoleId()))
                throw new BusinessException(messageSource.getMessage("user.role-id-cannot-be-empty", null,
                        LocaleContextHolder.getLocale()));

            role = roleServicePort.findById(request.getUser().getRoleId());
        }

        User user = userServicePort.create(request.getUser().to(role));
        Staff staff = staffServicePort.create(request.to(user));

        return StaffResponse.of(staff);
    }
}
