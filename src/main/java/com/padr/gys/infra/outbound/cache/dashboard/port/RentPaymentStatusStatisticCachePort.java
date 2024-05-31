package com.padr.gys.infra.outbound.cache.dashboard.port;

import java.util.List;

import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic;

public interface RentPaymentStatusStatisticCachePort {

    RentPaymentStatusStatistic save(RentPaymentStatusStatistic rentPaymentStatusStatistic);

    List<RentPaymentStatusStatistic> findAll();

    void deleteAll();
}
