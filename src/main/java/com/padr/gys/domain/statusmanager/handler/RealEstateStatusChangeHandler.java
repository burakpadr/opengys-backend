package com.padr.gys.domain.statusmanager.handler;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.status.constant.ForRentSubStatus;
import com.padr.gys.domain.status.constant.MainStatus;
import com.padr.gys.domain.statusmanager.model.StatusChangeModel;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RealEstateStatusChangeHandler implements StatusChangeHandler {

    private final RealEstatePersistencePort realEstatePersistencePort;

    @Override
    public void handle(StatusChangeModel model) {
        RealEstate oldEntity = (RealEstate) model.getOldEntity();

        switch (model.getType()) {
            case CREATE -> {
                if (oldEntity.getMainStatus().equals(MainStatus.FOR_RENT))
                    oldEntity.setForRentSubStatus(ForRentSubStatus.IN_PREPARATION);

                realEstatePersistencePort.save(oldEntity);
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + model.getType());
        }
    }
}
