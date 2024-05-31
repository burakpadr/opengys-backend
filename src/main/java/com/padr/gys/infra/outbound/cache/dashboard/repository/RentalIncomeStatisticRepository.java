package com.padr.gys.infra.outbound.cache.dashboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.dashboard.entity.RentalIncomeStatistic;

@Repository
public interface RentalIncomeStatisticRepository extends CrudRepository<RentalIncomeStatistic, String> {

}
