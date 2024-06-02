package com.padr.gys.domain.dashboard.entity;

import java.math.BigDecimal;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("RentPaymentStatusStatistic")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentPaymentStatusStatistic {
    
    // paid, unpaid, upcoming and pending

    private String id;
    private StatisticElement unpaidStatisticElement;
    private StatisticElement upcomingStatisticElement;
    private StatisticElement pendingStatisticsElement;

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatisticElement {

        private BigDecimal invoiceRevenue;
        private Long numberOfInvoice;
    }
}
