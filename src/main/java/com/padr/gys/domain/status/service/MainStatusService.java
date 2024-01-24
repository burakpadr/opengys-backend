package com.padr.gys.domain.status.service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.status.constant.MainStatus;
import com.padr.gys.domain.status.port.MainStatusServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainStatusService implements MainStatusServicePort {

    private final MessageSource messageSource;

    @Override
    public List<MainStatus> getMainStatus() {
        return Arrays.asList(MainStatus.values());
    }

    @Override
    public MainStatus getMainStatusByAlias(String alias) {
        try {
            return MainStatus.valueOf(alias);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(
                    messageSource.getMessage("status.not-found", null, LocaleContextHolder.getLocale()));
        }
    }

}
