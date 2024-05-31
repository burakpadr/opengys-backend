package com.padr.gys.infra.outbound.cache.dashboard.port;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic;
import com.padr.gys.infra.outbound.cache.dashboard.repository.RentPaymentStatusStatisticRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class RentPaymentStatusStatisticCachePortImpl implements RentPaymentStatusStatisticCachePort {

    private final RentPaymentStatusStatisticRepository rentPaymentStatusStatisticRepository;

    @Override
    public RentPaymentStatusStatistic save(RentPaymentStatusStatistic rentPaymentStatusStatistic) {
        return rentPaymentStatusStatisticRepository.save(rentPaymentStatusStatistic);
    }

    @Override
    public List<RentPaymentStatusStatistic> findAll() {
        return (List<RentPaymentStatusStatistic>) rentPaymentStatusStatisticRepository.findAll();
    }

    @Override
    public void deleteAll() {
        rentPaymentStatusStatisticRepository.deleteAll();
    }
}
