package com.padr.gys.infra.outbound.cache.dashboard.port;

import com.padr.gys.domain.dashboard.entity.OccupancyStatistic;
import com.padr.gys.domain.dashboard.entity.RentalIncomeStatistic;

import java.util.List;

public interface OccupancyStatisticCachePort {

    OccupancyStatistic save(OccupancyStatistic occupancyStatistic);

    List<OccupancyStatistic> findAll();

    void deleteAll();
}
