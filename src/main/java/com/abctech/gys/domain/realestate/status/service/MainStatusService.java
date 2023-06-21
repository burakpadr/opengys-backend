package com.abctech.gys.domain.realestate.status.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.abctech.gys.domain.realestate.status.constant.MainStatus;
import com.abctech.gys.domain.realestate.status.exception.StatusNotFoundException;
import com.abctech.gys.domain.realestate.status.port.MainStatusServicePort;

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
            throw new StatusNotFoundException(alias);
        }
    }
    
}
