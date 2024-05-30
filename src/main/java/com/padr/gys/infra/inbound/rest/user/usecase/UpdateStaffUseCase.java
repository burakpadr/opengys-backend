package com.padr.gys.infra.inbound.rest.user.usecase;

import java.util.NoSuchElementException;
import java.util.Objects;

import com.padr.gys.domain.user.entity.User;
import com.padr.gys.infra.outbound.persistence.rbac.port.RolePersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.StaffPersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.UserPersistencePort;
import jakarta.persistence.EntityExistsException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.common.exception.BusinessException;
import com.padr.gys.domain.rbac.entity.Role;
import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.infra.inbound.rest.user.model.request.UpdateStaffRequest;
import com.padr.gys.infra.inbound.rest.user.model.response.StaffResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateStaffUseCase {

    private final StaffPersistencePort staffPersistencePort;
    private UserPersistencePort userPersistencePort;
    private final RolePersistencePort rolePersistencePort;

    private final MessageSource messageSource;

    public StaffResponse execute(Long id, UpdateStaffRequest request) {
        Staff oldStaff = staffPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

        Role role = null;

        if (!request.getIsDeedOwner()) {
            if (Objects.isNull(request.getUser().getRoleId()))
                throw new BusinessException(messageSource.getMessage("user.role-id-cannot-be-empty", null,
                        LocaleContextHolder.getLocale()));

            role = rolePersistencePort.findById(request.getUser().getRoleId())
                    .orElseThrow(() -> new NoSuchElementException(
                            messageSource.getMessage("rbac.role.not-found", null, LocaleContextHolder.getLocale())));
        }

        // Update user

        User user = oldStaff.getUser();

        if (!user.getEmail().equals(request.getUser().getEmail())) {
            userPersistencePort.findByEmail(request.getUser().getEmail())
                    .ifPresent(u -> {
                        throw new EntityExistsException(
                                messageSource.getMessage("user.already-exist", null, LocaleContextHolder.getLocale()));
                    });
        }

        user.setName(request.getUser().getName());
        user.setSurname(request.getUser().getSurname());
        user.setRole(role);
        user.setEmail(request.getUser().getEmail());

        userPersistencePort.save(user);

        // Update staff

        oldStaff.setIsDeedOwner(request.getIsDeedOwner());

        staffPersistencePort.save(oldStaff);

        return StaffResponse.of(oldStaff);
    }
}
