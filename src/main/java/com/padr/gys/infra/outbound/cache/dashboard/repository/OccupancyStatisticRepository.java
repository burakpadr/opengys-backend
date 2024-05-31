package com.padr.gys.infra.outbound.cache.dashboard.repository;

import com.padr.gys.domain.dashboard.entity.OccupancyStatistic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupancyStatisticRepository extends CrudRepository<OccupancyStatistic, String> {
}
