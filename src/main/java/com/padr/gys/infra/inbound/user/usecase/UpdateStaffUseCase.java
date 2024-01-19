package com.padr.gys.infra.inbound.user.usecase;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.common.exception.BusinessException;
import com.padr.gys.domain.rbac.entity.Role;
import com.padr.gys.domain.rbac.port.RoleServicePort;
import com.padr.gys.domain.user.constant.UserExceptionMessage;
import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.domain.user.port.StaffServicePort;
import com.padr.gys.domain.user.port.UserServicePort;
import com.padr.gys.infra.inbound.user.model.request.UpdateStaffRequest;
import com.padr.gys.infra.inbound.user.model.response.StaffResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateStaffUseCase {
    
    private final UserServicePort userServicePort;
    private final StaffServicePort staffServicePort;
    private final RoleServicePort roleServicePort;

    public StaffResponse execute(Long id, UpdateStaffRequest request) {
        Staff oldStaff = staffServicePort.findById(id);

        Role role = null;

        if (!request.getIsDeedOwner()) {
            if (Objects.isNull(request.getUser().getRoleId()))
                throw new BusinessException(UserExceptionMessage.ROLE_ID_CANNOT_BE_EMPTY);
            
            role = roleServicePort.findById(request.getUser().getRoleId());
        }

        userServicePort.update(oldStaff.getUser(), request.getUser().to(role));
        staffServicePort.update(oldStaff, request.to(oldStaff.getUser()));

        return StaffResponse.of(oldStaff);
    }
}
