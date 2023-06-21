package com.abctech.gys.domain.realestate.status.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abctech.gys.domain.realestate.status.constant.MainStatus;
import com.abctech.gys.domain.realestate.status.port.MainStatusServicePort;

@Service
public class MainStatusService implements MainStatusServicePort {

    @Override
    public List<MainStatus> getMainStatus() {
        return Arrays.asList(MainStatus.values());
    }

    @Override
    public Optional<MainStatus> getMainStatusByAlias(String alias) {
        try {
            return Optional.of(MainStatus.valueOf(alias));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
    
}
