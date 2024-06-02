package com.padr.gys.domain.dashboard.handler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.dashboard.constant.EnumRentPaymentStatusStatisticElementType;
import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic;
import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic.StatisticElement;
import com.padr.gys.infra.outbound.cache.dashboard.port.RentPaymentStatusStatisticCachePort;
import com.padr.gys.infra.outbound.persistence.dashboard.port.DashboardPersistencePort;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RentPaymentStatusStatisticDashboardHandler
        implements DashboardHandler<RentPaymentStatusStatistic, EnumRentPaymentStatusStatisticElementType> {

    private final RentPaymentStatusStatisticCachePort rentPaymentStatusStatisticCachePort;
    private final DashboardPersistencePort dashboardPersistencePort;

    @Override
    public List<RentPaymentStatusStatistic> findAll() {
        return rentPaymentStatusStatisticCachePort.findAll();
    }

    @PostConstruct
    private void loadCache() {
        updateAllStatisticElements();
    }

    @Override
    public void updateStatisticElement(EnumRentPaymentStatusStatisticElementType statisticElementType) {
        switch (statisticElementType) {
            case UNPAID -> refreshUnpaidStatisticElement();
            case UPCOMING -> refreshUpcomingStatisticElement();
            case PENDING -> refreshPendingStatisticElement();
        }
    }

    private void refreshUnpaidStatisticElement() {
        RentPaymentStatusStatistic rentPaymentStatusStatistic = rentPaymentStatusStatisticCachePort.findAll().get(0);

        StatisticElement newUnpaidStatisticElement = dashboardPersistencePort.findUnpaidRentPaymentInvoices();

        rentPaymentStatusStatistic.setUnpaidStatisticElement(newUnpaidStatisticElement);

        rentPaymentStatusStatisticCachePort.save(rentPaymentStatusStatistic);
    }

    private void refreshUpcomingStatisticElement() {
        RentPaymentStatusStatistic rentPaymentStatusStatistic = rentPaymentStatusStatisticCachePort.findAll().get(0);

        StatisticElement newUpcomingStatisticElement = dashboardPersistencePort.findUpcomingRentPaymentInvoices();

        rentPaymentStatusStatistic.setUpcomingStatisticElement(newUpcomingStatisticElement);

        rentPaymentStatusStatisticCachePort.save(rentPaymentStatusStatistic);
    }

    private void refreshPendingStatisticElement() {
        RentPaymentStatusStatistic rentPaymentStatusStatistic = rentPaymentStatusStatisticCachePort.findAll().get(0);

        StatisticElement newPendingStatisticElement = dashboardPersistencePort.findPendingRentPaymentInvoices();

        rentPaymentStatusStatistic.setPendingStatisticsElement(newPendingStatisticElement);

        rentPaymentStatusStatisticCachePort.save(rentPaymentStatusStatistic);
    }

    @Override
    public void updateAllStatisticElements() {
        rentPaymentStatusStatisticCachePort.deleteAll();

        StatisticElement unpaidStatisticElement = dashboardPersistencePort.findUnpaidRentPaymentInvoices();
        StatisticElement upcomingStatisticElement = dashboardPersistencePort.findUpcomingRentPaymentInvoices();
        StatisticElement pendingStatisticElement = dashboardPersistencePort.findPendingRentPaymentInvoices();

        RentPaymentStatusStatistic rentPaymentStatusStatistic = RentPaymentStatusStatistic.builder()
                .unpaidStatisticElement(unpaidStatisticElement)
                .upcomingStatisticElement(upcomingStatisticElement)
                .pendingStatisticsElement(pendingStatisticElement)
                .build();

        rentPaymentStatusStatisticCachePort.save(rentPaymentStatusStatistic);
    }
}
