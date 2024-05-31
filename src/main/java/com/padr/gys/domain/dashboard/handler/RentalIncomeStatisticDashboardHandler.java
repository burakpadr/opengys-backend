package com.padr.gys.domain.dashboard.handler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.dashboard.constant.EnumRentalIncomeStatisticElementType;
import com.padr.gys.domain.dashboard.entity.RentalIncomeStatistic;
import com.padr.gys.domain.dashboard.entity.RentalIncomeStatistic.MonthlyStatisticElement;
import com.padr.gys.infra.outbound.cache.dashboard.port.RentalIncomeStatisticCachePort;
import com.padr.gys.infra.outbound.persistence.dashboard.port.DashboardPersistencePort;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RentalIncomeStatisticDashboardHandler
        implements DashboardHandler<RentalIncomeStatistic, EnumRentalIncomeStatisticElementType> {

    private final RentalIncomeStatisticCachePort rentalIncomeStatisticCachePort;
    private final DashboardPersistencePort dashboardPersistencePort;

    @Override
    public List<RentalIncomeStatistic> findAll() {
        return rentalIncomeStatisticCachePort.findAll();
    }

    @PostConstruct
    private void loadCache() {
        updateAllStatisticElements();
    }

    @Override
    public void updateStatisticElement(EnumRentalIncomeStatisticElementType statisticElementType) {
        switch (statisticElementType) {
            case MONTHLY -> refreshMonthStatisticElement();
        }
    }
    @Override
    public void updateAllStatisticElements() {
        rentalIncomeStatisticCachePort.deleteAll();

        List<MonthlyStatisticElement> monthlyStatisticElements = dashboardPersistencePort
                .calculateRentRevenueByMonths();

        RentalIncomeStatistic rentalIncomeStatistic = RentalIncomeStatistic.builder()
                .monthlyStatisticElements(monthlyStatisticElements)
                .build();

        rentalIncomeStatisticCachePort.save(rentalIncomeStatistic);
    }

    private void refreshMonthStatisticElement() {
        RentalIncomeStatistic rentalIncomeStatistic = rentalIncomeStatisticCachePort.findAll().get(0);

        List<MonthlyStatisticElement> newMonthlyStatisticElement = dashboardPersistencePort
                .calculateRentRevenueByMonths();

        rentalIncomeStatistic.setMonthlyStatisticElements(newMonthlyStatisticElement);

        rentalIncomeStatisticCachePort.save(rentalIncomeStatistic);
    }
}
