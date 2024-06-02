package com.padr.gys.infra.outbound.persistence.dashboard.port;

import java.util.List;

import com.padr.gys.domain.dashboard.entity.OccupancyStatistic;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.dashboard.entity.RealEstateDistributionByCategoriesStatistic;
import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic.StatisticElement;
import com.padr.gys.domain.dashboard.entity.RentalIncomeStatistic.MonthlyStatisticElement;
import com.padr.gys.infra.outbound.persistence.dashboard.repository.DashboardRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class DashboardPersistencePortImpl implements DashboardPersistencePort {
    
    private final DashboardRepository dashboardRepository;

    @Override
    public StatisticElement findUnpaidRentPaymentInvoices() {
        return dashboardRepository.findUnpaidRentPaymentInvoices();
    }

    @Override
    public StatisticElement findUpcomingRentPaymentInvoices() {
        return dashboardRepository.findUpcomingRentPaymentInvoices();
    }

    @Override
    public StatisticElement findPendingRentPaymentInvoices() {
        return dashboardRepository.findPendingRentPaymentInvoices();
    }

    @Override
    public List<MonthlyStatisticElement> calculateRentRevenueByMonths() {
        return dashboardRepository.calculateRentRevenueByMonths();
    }

    @Override
    public List<RealEstateDistributionByCategoriesStatistic.StatisticElement> findRealEstateDistrbutionByCategoryStatistics() {
        return dashboardRepository.findRealEstateDistrbutionByCategoryStatistics();
    }

    @Override
    public OccupancyStatistic findOccupancyStatistic() {
        return dashboardRepository.findOccupancyStatistic();
    }
}
