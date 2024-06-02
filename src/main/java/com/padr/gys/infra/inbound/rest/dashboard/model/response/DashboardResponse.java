package com.padr.gys.infra.inbound.rest.dashboard.model.response;

import com.padr.gys.domain.dashboard.entity.OccupancyStatistic;
import com.padr.gys.domain.dashboard.entity.RealEstateDistributionByCategoriesStatistic;
import com.padr.gys.domain.dashboard.entity.RentPaymentStatusStatistic;
import com.padr.gys.domain.dashboard.entity.RentalIncomeStatistic;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DashboardResponse {

    private RentPaymentStatusStatisticResponse rentPaymentStatusStatistic;
    private RentalIncomeStatisticResponse rentalIncomeStatistic;
    private RealEstateDistributionByCategoriesStatisticResponse realEstateDistributionByCategoriesStatistic;
    private OccupancyStatisticResponse occupancyStatistic;

    public static DashboardResponse of(RentPaymentStatusStatistic rentPaymentStatusStatistic,
                                       RentalIncomeStatistic rentalIncomeStatistic,
                                       RealEstateDistributionByCategoriesStatistic realEstateDistributionByCategoriesStatistic,
                                       OccupancyStatistic occupancyStatistic) {

        return DashboardResponse.builder()
                .rentPaymentStatusStatistic(RentPaymentStatusStatisticResponse.of(rentPaymentStatusStatistic))
                .rentalIncomeStatistic(RentalIncomeStatisticResponse.of(rentalIncomeStatistic))
                .realEstateDistributionByCategoriesStatistic(RealEstateDistributionByCategoriesStatisticResponse
                        .of(realEstateDistributionByCategoriesStatistic))
                .occupancyStatistic(OccupancyStatisticResponse.of(occupancyStatistic))
                .build();
    }

    @Data
    @Builder
    public static class RentPaymentStatusStatisticResponse {

        private RentPaymentStatusStatistic.StatisticElement paidStatisticElement;
        private RentPaymentStatusStatistic.StatisticElement unpaidStatisticElement;
        private RentPaymentStatusStatistic.StatisticElement upcomingStatisticElement;
        private RentPaymentStatusStatistic.StatisticElement pendingStatisticsElement;

        public static RentPaymentStatusStatisticResponse of(RentPaymentStatusStatistic rentPaymentStatusStatistic) {
            return RentPaymentStatusStatisticResponse.builder()
                    .unpaidStatisticElement(rentPaymentStatusStatistic.getUnpaidStatisticElement())
                    .upcomingStatisticElement(rentPaymentStatusStatistic.getUpcomingStatisticElement())
                    .pendingStatisticsElement(rentPaymentStatusStatistic.getPendingStatisticsElement())
                    .build();
        }
    }

    @Data
    @Builder
    public static class RentalIncomeStatisticResponse {
        private List<RentalIncomeStatistic.MonthlyStatisticElement> monthlyStatisticElements;

        public static RentalIncomeStatisticResponse of(RentalIncomeStatistic rentalIncomeStatistic) {
            return RentalIncomeStatisticResponse.builder()
                    .monthlyStatisticElements(rentalIncomeStatistic.getMonthlyStatisticElements())
                    .build();
        }
    }

    @Data
    @Builder
    public static class RealEstateDistributionByCategoriesStatisticResponse {

        private List<RealEstateDistributionByCategoriesStatistic.StatisticElement> statisticElements;

        public static RealEstateDistributionByCategoriesStatisticResponse of(
                RealEstateDistributionByCategoriesStatistic realEstateDistributionByCategoriesStatistic) {

            return RealEstateDistributionByCategoriesStatisticResponse.builder()
                    .statisticElements(realEstateDistributionByCategoriesStatistic.getStatisticElements())
                    .build();
        }
    }

    @Data
    @Builder
    public static class OccupancyStatisticResponse {

        private Long realEstateCount;
        private Long occupancyCount;

        public static OccupancyStatisticResponse of(OccupancyStatistic occupancyStatistic) {
            return OccupancyStatisticResponse.builder()
                    .realEstateCount(occupancyStatistic.getRealEstateCount())
                    .occupancyCount(occupancyStatistic.getOccupancyCount())
                    .build();
        }
    }
}
