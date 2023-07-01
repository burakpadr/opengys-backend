package com.padr.gys.domain.realestate.status.port;

import java.util.List;

import com.padr.gys.domain.realestate.status.constant.MainStatus;

public interface MainStatusServicePort {
    
    List<MainStatus> getMainStatus();

    MainStatus getMainStatusByAlias(String alias);
}
