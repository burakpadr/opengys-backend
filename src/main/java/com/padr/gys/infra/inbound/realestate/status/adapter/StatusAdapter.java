package com.padr.gys.infra.inbound.realestate.status.adapter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.realestate.status.model.response.StatusResponse;
import com.padr.gys.infra.inbound.realestate.status.model.response.SubStatusResponse;
import com.padr.gys.infra.inbound.realestate.status.usecase.FindStatusUseCase;
import com.padr.gys.infra.inbound.realestate.status.usecase.FindSubStatusUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
public class StatusAdapter {

    private final FindStatusUseCase findStatusUseCase;
    private final FindSubStatusUseCase findSubStatusUseCase;

    @GetMapping
    public List<StatusResponse> getStatus() {
        return findStatusUseCase.execute();
    }

    @GetMapping("/{mainStatusAlias}/sub-status")
    public List<SubStatusResponse> getSubStatusByMainStatusAlias(@PathVariable String mainStatusAlias) {
        return findSubStatusUseCase.execute(mainStatusAlias);
    }
}
