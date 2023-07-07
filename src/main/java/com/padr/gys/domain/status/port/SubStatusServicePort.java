package com.padr.gys.domain.status.port;

import java.util.List;

import com.padr.gys.domain.status.constant.SubStatus;

public interface SubStatusServicePort {
    
    List<SubStatus> getForRentSubStatus();

    List<SubStatus> getForSaleSubStatus();
}
