package com.padr.gys.domain.status.service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.status.constant.MainStatus;
import com.padr.gys.domain.status.constant.StatusExceptionMessage;
import com.padr.gys.domain.status.port.MainStatusServicePort;

@Service
public class MainStatusService implements MainStatusServicePort {

    @Override
    public List<MainStatus> getMainStatus() {
        return Arrays.asList(MainStatus.values());
    }

    @Override
    public MainStatus getMainStatusByAlias(String alias) {
        try {
            return MainStatus.valueOf(alias);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(StatusExceptionMessage.STATUS_NOT_FOUND);
        }
    }
    
}
