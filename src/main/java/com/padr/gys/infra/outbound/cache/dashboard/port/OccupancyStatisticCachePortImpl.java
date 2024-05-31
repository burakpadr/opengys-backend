package com.padr.gys.infra.outbound.cache.dashboard.port;

import com.padr.gys.domain.dashboard.entity.OccupancyStatistic;
import com.padr.gys.infra.outbound.cache.dashboard.repository.OccupancyStatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class OccupancyStatisticCachePortImpl implements OccupancyStatisticCachePort {

    private final OccupancyStatisticRepository occupancyStatisticRepository;

    @Override
    public OccupancyStatistic save(OccupancyStatistic occupancyStatistic) {
        return occupancyStatisticRepository.save(occupancyStatistic);
    }

    @Override
    public List<OccupancyStatistic> findAll() {
        return (List<OccupancyStatistic>) occupancyStatisticRepository.findAll();
    }

    @Override
    public void deleteAll() {
        occupancyStatisticRepository.deleteAll();
    }
}
