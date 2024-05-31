package com.padr.gys.domain.dashboard.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("RentalIncomeStatistic")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalIncomeStatistic {
    
    // monthly rental income

    private String id;

    @Builder.Default
    private List<MonthlyStatisticElement> monthlyStatisticElements = new ArrayList<>();

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MonthlyStatisticElement {

        private String month;
        private BigDecimal revenue;
    }
}
