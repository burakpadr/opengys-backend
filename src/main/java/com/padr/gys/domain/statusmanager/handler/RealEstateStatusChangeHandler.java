package com.padr.gys.domain.statusmanager.handler;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.status.constant.ForRentSubStatus;
import com.padr.gys.domain.status.constant.MainStatus;
import com.padr.gys.domain.statusmanager.model.StatusChangeReportModel;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RealEstateStatusChangeHandler extends StatusChangeHandler<StatusChangeReportModel<RealEstate>> {

    private final RealEstatePersistencePort realEstatePersistencePort;

    @Override
    public void onNext(StatusChangeReportModel<RealEstate> item) {
        switch (item.getType()) {
            case CREATE -> {
                if (item.getOldEntity().getMainStatus().equals(MainStatus.FOR_RENT))
                    item.getOldEntity().setForRentSubStatus(ForRentSubStatus.IN_PREPARATION);

                realEstatePersistencePort.save(item.getOldEntity());
            }
        }

        subscription.request(1);
    }
}
