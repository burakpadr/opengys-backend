package com.padr.gys.infra.outbound.cache.dashboard.port;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.dashboard.entity.RentalIncomeStatistic;
import com.padr.gys.infra.outbound.cache.dashboard.repository.RentalIncomeStatisticRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class RentalIncomeStatisticCachePortImpl implements RentalIncomeStatisticCachePort {

    private final RentalIncomeStatisticRepository rentalIncomeStatisticRepository;

    @Override
    public RentalIncomeStatistic save(RentalIncomeStatistic rentalIncomeStatistic) {
        return rentalIncomeStatisticRepository.save(rentalIncomeStatistic);
    }

    @Override
    public List<RentalIncomeStatistic> findAll() {
        return (List<RentalIncomeStatistic>) rentalIncomeStatisticRepository.findAll();
    }

    @Override
    public void deleteAll() {
        rentalIncomeStatisticRepository.deleteAll();
    }
}
