package com.abctech.gys.domain.realestate.status.port;

import java.util.List;

import com.abctech.gys.domain.realestate.status.constant.SubStatus;

public interface SubStatusServicePort {
    
    List<SubStatus> getForRentSubStatus();

    List<SubStatus> getForSaleSubStatus();
}
