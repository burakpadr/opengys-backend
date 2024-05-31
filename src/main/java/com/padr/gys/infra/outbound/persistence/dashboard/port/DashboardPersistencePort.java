package com.padr.gys.infra.outbound.persistence.dashboard.port;

import java.util.List;

import com.padr.gys.domain.dashboard.entity.OccupancyStatistic;
import com.padr.gys.domain.dashboard.entity.RealEstateDistributionByCategoriesStatistic;
import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic;
import com.padr.gys.domain.dashboard.entity.RentalIncomeStatistic;

public interface DashboardPersistencePort {

    RentPaymentStatusStatistic.StatisticElement findPaidRentPaymentInvoices();

    RentPaymentStatusStatistic.StatisticElement findUnpaidRentPaymentInvoices();

    RentPaymentStatusStatistic.StatisticElement findUpcomingRentPaymentInvoices();

    RentPaymentStatusStatistic.StatisticElement findPendingRentPaymentInvoices();

    List<RentalIncomeStatistic.MonthlyStatisticElement> calculateRentRevenueByMonths();

    List<RealEstateDistributionByCategoriesStatistic.StatisticElement> findRealEstateDistrbutionByCategoryStatistics();

    OccupancyStatistic findOccupancyStatistic();
}
