package com.padr.gys.infra.inbound.scheduled.dashboard.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.dashboard.constant.EnumRentPaymentStatusStatisticElementType;
import com.padr.gys.domain.dashboard.context.DashboardHandlerContext;
import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DashboardScheduler {

    // This scheduled job runs every day at 1am

    @Scheduled(cron = "0 0 0 * * *")
    public void refreshDashboardStatisticElementsEveryDay() {
        DashboardHandlerContext.getDashboardHandler(RentPaymentStatusStatistic.class)
                .updateStatisticElement(EnumRentPaymentStatusStatisticElementType.UNPAID);

        DashboardHandlerContext.getDashboardHandler(RentPaymentStatusStatistic.class)
                .updateStatisticElement(EnumRentPaymentStatusStatisticElementType.UPCOMING);
    }
}
