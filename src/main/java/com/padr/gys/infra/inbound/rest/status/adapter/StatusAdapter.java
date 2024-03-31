package com.padr.gys.infra.inbound.rest.status.adapter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.rest.status.model.response.StatusResponse;
import com.padr.gys.infra.inbound.rest.status.usecase.FindStatusUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/status")
@RequiredArgsConstructor
public class StatusAdapter {

    private final FindStatusUseCase findStatusUseCase;

    @GetMapping
    public List<StatusResponse> getStatus() {
        return findStatusUseCase.execute();
    }
}
