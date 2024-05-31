package com.padr.gys.domain.dashboard.context;

import java.util.HashMap;
import java.util.Map;

import com.padr.gys.domain.dashboard.entity.OccupancyStatistic;
import com.padr.gys.domain.dashboard.entity.RealEstateDistributionByCategoriesStatistic;
import com.padr.gys.domain.dashboard.handler.*;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic;
import com.padr.gys.domain.dashboard.entity.RentalIncomeStatistic;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DashboardHandlerContext {

    private static final Map<Class<?>, DashboardHandler<?, ?>> handlers = new HashMap<>();

    private final RentPaymentStatusStatisticDashboardHandler rentPaymentStatusStatisticDashboardHandler;
    private final RentalIncomeStatisticDashboardHandler rentalIncomeStatisticDashboardHandler;
    private final RealEstateDistributionByCategoriesStatisticDashboardHandler realEstateDistributionByCategoriesStatisticDashboardHandler;
    private final OccupancyStatisticDashboardHandler occupancyStatisticDashboardHandler;

    @SuppressWarnings("unchecked")
    public static <T, R> DashboardHandler<T, R> getDashboardHandler(Class<T> clazz) {
        return (DashboardHandler<T, R>) handlers.get(clazz);
    }

    @PostConstruct
    private void initHandlers() {
        handlers.put(RentPaymentStatusStatistic.class, rentPaymentStatusStatisticDashboardHandler);
        handlers.put(RentalIncomeStatistic.class, rentalIncomeStatisticDashboardHandler);
        handlers.put(RealEstateDistributionByCategoriesStatistic.class, realEstateDistributionByCategoriesStatisticDashboardHandler);
        handlers.put(OccupancyStatistic.class, occupancyStatisticDashboardHandler);
    }
}
