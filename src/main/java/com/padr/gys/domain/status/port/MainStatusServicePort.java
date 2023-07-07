package com.padr.gys.domain.status.port;

import java.util.List;

import com.padr.gys.domain.status.constant.MainStatus;

public interface MainStatusServicePort {
    
    List<MainStatus> getMainStatus();

    MainStatus getMainStatusByAlias(String alias);
}
