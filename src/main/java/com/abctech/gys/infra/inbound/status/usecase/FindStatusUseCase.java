package com.abctech.gys.infra.inbound.status.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.abctech.gys.domain.realestate.status.port.MainStatusServicePort;
import com.abctech.gys.infra.inbound.status.model.response.StatusResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindStatusUseCase {

    private final MainStatusServicePort mainStatusServicePort;

    public List<StatusResponse> execute() {
        return mainStatusServicePort.getMainStatus().stream().map(StatusResponse::of).toList();
    }
}
