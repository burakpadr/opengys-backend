package com.padr.gys.infra.inbound.rest.dashboard.usecase;

import com.padr.gys.domain.dashboard.entity.OccupancyStatistic;
import com.padr.gys.domain.dashboard.entity.RealEstateDistributionByCategoriesStatistic;
import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic;
import com.padr.gys.domain.dashboard.entity.RentalIncomeStatistic;
import com.padr.gys.domain.dashboard.handler.OccupancyStatisticDashboardHandler;
import com.padr.gys.domain.dashboard.handler.RealEstateDistributionByCategoriesStatisticDashboardHandler;
import com.padr.gys.domain.dashboard.handler.RentPaymentStatusStatisticDashboardHandler;
import com.padr.gys.domain.dashboard.handler.RentalIncomeStatisticDashboardHandler;
import com.padr.gys.infra.inbound.rest.dashboard.model.response.DashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetDashboardStatisticsUsecase {

    private final RentPaymentStatusStatisticDashboardHandler rentPaymentStatusStatisticDashboardHandler;
    private final RentalIncomeStatisticDashboardHandler rentalIncomeStatisticDashboardHandler;
    private final RealEstateDistributionByCategoriesStatisticDashboardHandler realEstateDistributionByCategoriesStatisticDashboardHandler;
    private final OccupancyStatisticDashboardHandler occupancyStatisticDashboardHandler;

    public DashboardResponse execute() {
        RentPaymentStatusStatistic rentPaymentStatusStatistic = rentPaymentStatusStatisticDashboardHandler
                .findAll().get(0);

        RentalIncomeStatistic rentalIncomeStatistic = rentalIncomeStatisticDashboardHandler.findAll().get(0);

        RealEstateDistributionByCategoriesStatistic realEstateDistributionByCategoriesStatistic = realEstateDistributionByCategoriesStatisticDashboardHandler
                .findAll().get(0);

        OccupancyStatistic occupancyStatistic = occupancyStatisticDashboardHandler.findAll().get(0);

        return DashboardResponse.of(rentPaymentStatusStatistic,
                rentalIncomeStatistic,
                realEstateDistributionByCategoriesStatistic,
                occupancyStatistic);
    }
}
