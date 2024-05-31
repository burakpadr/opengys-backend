package com.padr.gys.infra.inbound.rest.dashboard.adapter;

import com.padr.gys.infra.inbound.rest.dashboard.model.response.DashboardResponse;
import com.padr.gys.infra.inbound.rest.dashboard.usecase.GetDashboardStatisticsUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gys/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardAdapter {

    private final GetDashboardStatisticsUsecase getDashboardStatisticsUsecase;

    @GetMapping
    public DashboardResponse getDashboardStatistics() {
        return getDashboardStatisticsUsecase.execute();
    }
}
