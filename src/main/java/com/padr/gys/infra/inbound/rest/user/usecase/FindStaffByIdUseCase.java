package com.padr.gys.infra.inbound.rest.user.usecase;

import com.padr.gys.domain.user.entity.Staff;
import com.padr.gys.infra.outbound.persistence.user.port.StaffPersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.user.model.response.StaffResponse;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class FindStaffByIdUseCase {
    
    private final StaffPersistencePort staffPersistencePort;

    private final MessageSource messageSource;

    public StaffResponse execute(Long id) {
        Staff staff = staffPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("user.not-found", null, LocaleContextHolder.getLocale())));

        return StaffResponse.of(staff);
    }
}
