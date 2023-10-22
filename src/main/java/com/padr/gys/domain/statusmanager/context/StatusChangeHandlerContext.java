package com.padr.gys.domain.statusmanager.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.statusmanager.handler.AdvertStatusChangeHandler;
import com.padr.gys.domain.statusmanager.handler.RealEstateStatusChangeHandler;
import com.padr.gys.domain.statusmanager.handler.RentalContractStatusChangeHandler;
import com.padr.gys.domain.statusmanager.handler.StatusChangeHandler;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StatusChangeHandlerContext {

    private Map<Class<?>, StatusChangeHandler> statusChangeHandlers;

    private final RealEstateStatusChangeHandler realEstateStatusChangeHandler;
    private final AdvertStatusChangeHandler advertStatusChangeHandler;
    private final RentalContractStatusChangeHandler rentalContractStatusChangeHandler;

    public StatusChangeHandler getStatusChangeHandler(Class<?> entityClass) {
        return statusChangeHandlers.get(entityClass);
    }

    @PostConstruct
    private void initializeContext() {
        statusChangeHandlers = new HashMap<>();

        statusChangeHandlers.put(RealEstate.class, realEstateStatusChangeHandler);
        statusChangeHandlers.put(Advert.class, advertStatusChangeHandler);
        statusChangeHandlers.put(RentalContract.class, rentalContractStatusChangeHandler);
    }
}
