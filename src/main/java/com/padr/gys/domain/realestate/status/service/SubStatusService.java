package com.padr.gys.domain.realestate.status.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.realestate.status.constant.ForRentSubStatus;
import com.padr.gys.domain.realestate.status.constant.ForSaleSubStatus;
import com.padr.gys.domain.realestate.status.constant.SubStatus;
import com.padr.gys.domain.realestate.status.port.SubStatusServicePort;

@Service
public class SubStatusService implements SubStatusServicePort {

    @Override
    public List<SubStatus> getForRentSubStatus() {
        List<SubStatus> subStatus = Arrays.asList(ForRentSubStatus.values());

        sortSubStatusByOrder(subStatus);

        return subStatus;
    }

    @Override
    public List<SubStatus> getForSaleSubStatus() {
        List<SubStatus> subStatus = Arrays.asList(ForSaleSubStatus.values());

        sortSubStatusByOrder(subStatus);

        return subStatus;
    }

    private void sortSubStatusByOrder(List<SubStatus> subStatus) {
        Collections.sort(subStatus, Comparator.comparingInt(SubStatus::getOrder));

    }
}
