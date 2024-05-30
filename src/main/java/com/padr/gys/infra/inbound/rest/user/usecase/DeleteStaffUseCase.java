package com.padr.gys.infra.inbound.rest.user.usecase;

import com.padr.gys.infra.outbound.persistence.user.port.StaffPersistencePort;
import com.padr.gys.infra.outbound.persistence.user.port.UserPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.user.entity.Staff;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class DeleteStaffUseCase {
    
    private final StaffPersistencePort staffPersistencePort;
    private final UserPersistencePort userPersistencePort;

    private final MessageSource messageSource;

    public void execute(Long id) {
        Staff staff = staffPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

        // Delete staff

        staff.setIsDeleted(true);

        staffPersistencePort.save(staff);

        // Delete user

        staff.getUser().setIsDeleted(true);

        userPersistencePort.save(staff.getUser());
    }
}
