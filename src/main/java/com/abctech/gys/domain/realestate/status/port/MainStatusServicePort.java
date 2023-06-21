package com.abctech.gys.domain.realestate.status.port;

import java.util.List;
import java.util.Optional;

import com.abctech.gys.domain.realestate.status.constant.MainStatus;

public interface MainStatusServicePort {
    
    List<MainStatus> getMainStatus();

    Optional<MainStatus> getMainStatusByAlias(String alias);
}
