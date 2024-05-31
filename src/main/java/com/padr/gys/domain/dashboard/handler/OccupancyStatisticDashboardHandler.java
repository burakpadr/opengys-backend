package com.padr.gys.domain.dashboard.handler;

import com.padr.gys.domain.dashboard.entity.OccupancyStatistic;
import com.padr.gys.infra.outbound.cache.dashboard.port.OccupancyStatisticCachePort;
import com.padr.gys.infra.outbound.persistence.dashboard.port.DashboardPersistencePort;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OccupancyStatisticDashboardHandler implements DashboardHandler<OccupancyStatistic, Void> {

    private final OccupancyStatisticCachePort occupancyStatisticCachePort;
    private final DashboardPersistencePort dashboardPersistencePort;

    @PostConstruct
    private void loadCache() {
        updateAllStatisticElements();
    }

    @Override
    public List<OccupancyStatistic> findAll() {
        return occupancyStatisticCachePort.findAll();
    }

    @Override
    public void updateStatisticElement(Void statisticElementType) {

    }

    @Override
    public void updateAllStatisticElements() {
        occupancyStatisticCachePort.deleteAll();

        OccupancyStatistic occupancyStatistic = dashboardPersistencePort.findOccupancyStatistic();

        occupancyStatisticCachePort.save(occupancyStatistic);
    }
}
