package com.padr.gys.infra.outbound.cache.dashboard.port;

import com.padr.gys.domain.dashboard.entity.RealEstateDistributionByCategoriesStatistic;
import com.padr.gys.domain.dashboard.entity.RentalIncomeStatistic;

import java.util.List;

public interface RealEstateDistributionByCategoriesStatisticCachePort {

    RealEstateDistributionByCategoriesStatistic save(RealEstateDistributionByCategoriesStatistic realEstateDistributionByCategoriesStatistic);

    List<RealEstateDistributionByCategoriesStatistic> findAll();

    void deleteAll();
}
