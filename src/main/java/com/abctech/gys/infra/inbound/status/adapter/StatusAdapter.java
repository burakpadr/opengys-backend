package com.abctech.gys.infra.inbound.status.adapter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abctech.gys.infra.inbound.status.model.response.StatusResponse;
import com.abctech.gys.infra.inbound.status.model.response.SubStatusResponse;
import com.abctech.gys.infra.inbound.status.usecase.FindStatusUseCase;
import com.abctech.gys.infra.inbound.status.usecase.FindSubStatusUseCase;

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
