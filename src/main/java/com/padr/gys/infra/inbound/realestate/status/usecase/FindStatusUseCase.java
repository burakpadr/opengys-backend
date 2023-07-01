package com.padr.gys.infra.inbound.realestate.status.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.status.port.MainStatusServicePort;
import com.padr.gys.infra.inbound.realestate.status.model.response.StatusResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindStatusUseCase {

    private final MainStatusServicePort mainStatusServicePort;

    public List<StatusResponse> execute() {
        return mainStatusServicePort.getMainStatus().stream().map(StatusResponse::of).toList();
    }
}
