package com.padr.gys.infra.inbound.rest.status.usecase;

import java.util.Arrays;
import java.util.List;

import com.padr.gys.domain.status.constant.MainStatus;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.status.model.response.StatusResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindStatusUseCase {

    public List<StatusResponse> execute() {
        return Arrays.stream(MainStatus.values()).map(StatusResponse::of).toList();
    }
}
