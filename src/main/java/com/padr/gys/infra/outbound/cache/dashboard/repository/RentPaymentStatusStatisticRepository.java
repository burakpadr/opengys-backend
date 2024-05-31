package com.padr.gys.infra.outbound.cache.dashboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic;

@Repository
public interface RentPaymentStatusStatisticRepository extends CrudRepository<RentPaymentStatusStatistic, String> {

}
