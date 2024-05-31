package com.padr.gys.domain.dashboard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("RentalIncomeStatistic")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OccupancyStatistic {

    private String id;
    private Long realEstateCount;
    private Long occupancyCount;
}
