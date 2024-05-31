package com.padr.gys.domain.dashboard.handler;

import java.util.List;

public interface DashboardHandler<T, R> {

    List<T> findAll();

    void updateStatisticElement(R statisticElementType);

    void updateAllStatisticElements();
}
