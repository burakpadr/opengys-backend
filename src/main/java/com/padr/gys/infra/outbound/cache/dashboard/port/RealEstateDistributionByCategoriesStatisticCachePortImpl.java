package com.padr.gys.infra.outbound.cache.dashboard.port;

import com.padr.gys.domain.dashboard.entity.RealEstateDistributionByCategoriesStatistic;
import com.padr.gys.infra.outbound.cache.dashboard.repository.RealEstateDistributionByCategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class RealEstateDistributionByCategoriesStatisticCachePortImpl implements RealEstateDistributionByCategoriesStatisticCachePort {

    private final RealEstateDistributionByCategoriesRepository realEstateDistributionByCategoriesRepository;

    @Override
    public RealEstateDistributionByCategoriesStatistic save(RealEstateDistributionByCategoriesStatistic realEstateDistributionByCategoriesStatistic) {
        return realEstateDistributionByCategoriesRepository.save(realEstateDistributionByCategoriesStatistic);
    }

    @Override
    public List<RealEstateDistributionByCategoriesStatistic> findAll() {
        return (List<RealEstateDistributionByCategoriesStatistic>) realEstateDistributionByCategoriesRepository.findAll();
    }

    @Override
    public void deleteAll() {
        realEstateDistributionByCategoriesRepository.deleteAll();
    }
}
