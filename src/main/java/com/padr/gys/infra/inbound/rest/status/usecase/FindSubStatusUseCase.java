package com.padr.gys.infra.inbound.rest.status.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.status.constant.MainStatus;
import com.padr.gys.domain.status.constant.SubStatus;
import com.padr.gys.domain.status.port.MainStatusServicePort;
import com.padr.gys.domain.status.port.SubStatusServicePort;
import com.padr.gys.infra.inbound.rest.status.model.response.SubStatusResponse;

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
