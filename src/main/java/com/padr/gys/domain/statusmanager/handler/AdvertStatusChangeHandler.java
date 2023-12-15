package com.padr.gys.domain.statusmanager.handler;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.status.constant.ForRentSubStatus;
import com.padr.gys.domain.status.constant.SubStatus;
import com.padr.gys.domain.statusmanager.model.StatusChangeModel;
import com.padr.gys.infra.outbound.persistence.advert.port.AdvertPersistencePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdvertStatusChangeHandler implements StatusChangeHandler {

    private final RealEstatePersistencePort realEstatePersistencePort;
    private final AdvertPersistencePort advertPersistencePort;

    @Override
    public void handle(StatusChangeModel model) {
        Advert oldEntity = (Advert) model.getOldEntity();

        // switch (model.getType()) {
        //     case CREATE -> {
        //         SubStatus currentSubStatus = oldEntity.getRealEstate().getSubStatus();

        //         if (currentSubStatus instanceof  ForRentSubStatus)
        //             if (ForRentSubStatus.IN_NOTICE.getOrder() > currentSubStatus.getOrder())
        //                 oldEntity.getRealEstate().setForRentSubStatus(ForRentSubStatus.IN_NOTICE);

        //         realEstatePersistencePort.save(oldEntity.getRealEstate());
        //     }
        //     case UPDATE, DELETE -> {
        //         SubStatus currentSubStatus = oldEntity.getRealEstate().getSubStatus();

        //         if (currentSubStatus instanceof ForRentSubStatus) {
        //             boolean anAdvertIsPublished = advertPersistencePort.
        //                     findByRealEstateIdAndIsActive(oldEntity.getId(), true)
        //                     .stream()
        //                     .anyMatch(Advert::getIsPublished);

        //             if (currentSubStatus.equals(ForRentSubStatus.IN_NOTICE) && !anAdvertIsPublished)
        //                 oldEntity.getRealEstate().setForRentSubStatus(ForRentSubStatus.IN_PREPARATION);
        //         }

        //         realEstatePersistencePort.save(oldEntity.getRealEstate());
        //     }
        // }
    }
}
