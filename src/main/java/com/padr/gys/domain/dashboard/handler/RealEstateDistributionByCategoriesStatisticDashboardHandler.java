package com.padr.gys.domain.dashboard.handler;

import com.padr.gys.domain.dashboard.entity.RealEstateDistributionByCategoriesStatistic;
import com.padr.gys.infra.outbound.cache.dashboard.port.RealEstateDistributionByCategoriesStatisticCachePort;
import com.padr.gys.infra.outbound.persistence.dashboard.port.DashboardPersistencePort;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RealEstateDistributionByCategoriesStatisticDashboardHandler
        implements DashboardHandler<RealEstateDistributionByCategoriesStatistic, Void>  {

    private final DashboardPersistencePort dashboardPersistencePort;
    private final RealEstateDistributionByCategoriesStatisticCachePort realEstateDistributionByCategoriesStatisticCachePort;

    @PostConstruct
    private void loadCache() {
        updateAllStatisticElements();
    }

    @Override
    public List<RealEstateDistributionByCategoriesStatistic> findAll() {
        return realEstateDistributionByCategoriesStatisticCachePort.findAll();
    }

    @Override
    public void updateStatisticElement(Void statisticElementType) {

    }

    @Override
    public void updateAllStatisticElements() {
        realEstateDistributionByCategoriesStatisticCachePort.deleteAll();

        List<RealEstateDistributionByCategoriesStatistic.StatisticElement> statisticElements = dashboardPersistencePort
                .findRealEstateDistrbutionByCategoryStatistics();


        RealEstateDistributionByCategoriesStatistic statistic = RealEstateDistributionByCategoriesStatistic.builder()
                .statisticElements(statisticElements)
                .build();

        realEstateDistributionByCategoriesStatisticCachePort.save(statistic);
    }
}
