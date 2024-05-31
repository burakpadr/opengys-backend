package com.padr.gys.infra.outbound.cache.dashboard.port;

import java.util.List;

import com.padr.gys.domain.dashboard.entity.RentalIncomeStatistic;

public interface RentalIncomeStatisticCachePort {

    RentalIncomeStatistic save(RentalIncomeStatistic rentalIncomeStatistic);

    List<RentalIncomeStatistic> findAll();

    void deleteAll();
}
