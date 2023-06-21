package com.abctech.gys.infra.inbound.status.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.abctech.gys.domain.realestate.status.constant.MainStatus;
import com.abctech.gys.domain.realestate.status.constant.SubStatus;
import com.abctech.gys.domain.realestate.status.port.MainStatusServicePort;
import com.abctech.gys.domain.realestate.status.port.SubStatusServicePort;
import com.abctech.gys.infra.inbound.status.model.response.SubStatusResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindSubStatusUseCase {

    private final MainStatusServicePort mainStatusServicePort;
    private final SubStatusServicePort subStatusServicePort;

    public List<SubStatusResponse> execute(String mainStatusAlias) {
        MainStatus mainStatus = mainStatusServicePort.getMainStatusByAlias(mainStatusAlias);

        List<SubStatus> subStatus = switch (mainStatus) {
            case FOR_RENT -> subStatusServicePort.getForRentSubStatus();
            case FOR_SALE -> subStatusServicePort.getForSaleSubStatus();
            default -> List.of();
        };

        return subStatus.stream().map(SubStatusResponse::of).toList();
    }
}
