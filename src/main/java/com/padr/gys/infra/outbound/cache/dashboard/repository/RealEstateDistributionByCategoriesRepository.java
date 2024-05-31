package com.padr.gys.infra.outbound.cache.dashboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.dashboard.entity.RealEstateDistributionByCategoriesStatistic;

@Repository
public interface RealEstateDistributionByCategoriesRepository
        extends CrudRepository<RealEstateDistributionByCategoriesStatistic, String> {

}
