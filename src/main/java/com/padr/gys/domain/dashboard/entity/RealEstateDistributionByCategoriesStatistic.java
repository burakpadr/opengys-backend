package com.padr.gys.domain.dashboard.entity;

import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("RealEstateDistributionByCategoriesStatistic")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealEstateDistributionByCategoriesStatistic {

    private String id;
    private List<StatisticElement> statisticElements;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StatisticElement {

        private String categoryName;
        private Long numberOfRealEstate;
    }
}
