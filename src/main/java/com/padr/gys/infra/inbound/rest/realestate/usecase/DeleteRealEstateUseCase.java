package com.padr.gys.infra.inbound.rest.realestate.usecase;

import com.padr.gys.domain.dashboard.context.DashboardHandlerContext;
import com.padr.gys.domain.dashboard.entity.OccupancyStatistic;
import com.padr.gys.domain.dashboard.entity.RealEstateDistributionByCategoriesStatistic;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class DeleteRealEstateUseCase {
    
    private final RealEstatePersistencePort realEstatePersistencePort;

    private final MessageSource messageSource;
    public void execute(Long realEstateId) {
        RealEstate realEstate = realEstatePersistencePort.findById(realEstateId)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("realestate.not-found", null, LocaleContextHolder.getLocale())));

        realEstate.setIsDeleted(true);

        realEstatePersistencePort.save(realEstate);

        DashboardHandlerContext.getDashboardHandler(RealEstateDistributionByCategoriesStatistic.class)
                .updateAllStatisticElements();

        DashboardHandlerContext.getDashboardHandler(OccupancyStatistic.class).updateAllStatisticElements();
    } 
}
