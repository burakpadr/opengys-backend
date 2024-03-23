package com.padr.gys.domain.statusmanager.handler;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.status.constant.ForRentSubStatus;
import com.padr.gys.domain.status.constant.SubStatus;
import com.padr.gys.domain.statusmanager.model.StatusChangeModel;
import com.padr.gys.infra.outbound.persistence.advert.port.AdvertPersistencePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RentalContractStatusChangeHandler implements StatusChangeHandler {

    private final RealEstatePersistencePort realEstatePersistencePort;
    private final AdvertPersistencePort advertPersistencePort;

    @Override
    public void handle(StatusChangeModel model) {
    //     RentalContract oldEntity = (RentalContract) model.getOldEntity();
    //     RentalContract updatedEntity = (RentalContract) model.getUpdatedEntity();

    //     switch (model.getType()) {
    //         case CREATE -> {
    //             SubStatus currentSubStatus = oldEntity.getRealEstate().getSubStatus();

    //             if (currentSubStatus instanceof ForRentSubStatus)
    //                 if (ForRentSubStatus.RENTED.getOrder() > currentSubStatus.getOrder())
    //                     oldEntity.getRealEstate().setForRentSubStatus(ForRentSubStatus.RENTED);

    //             realEstatePersistencePort.save(oldEntity.getRealEstate());
    //         }
    //         case UPDATE -> {
    //             SubStatus currentSubStatus = oldEntity.getRealEstate().getSubStatus();

    //             if (currentSubStatus instanceof ForRentSubStatus) {
    //                 boolean anAdvertIsPublished = advertPersistencePort
    //                         .findByRealEstateId(oldEntity.getRealEstate().getId())
    //                         .stream()
    //                         .anyMatch(Advert::getIsPublished);

    //                 if (updatedEntity.getIsPublished())
    //                     updatedEntity.getRealEstate().setForRentSubStatus(ForRentSubStatus.RENTED);
    //                 else if (anAdvertIsPublished)
    //                     updatedEntity.getRealEstate().setForRentSubStatus(ForRentSubStatus.IN_NOTICE);
    //                 else
    //                     updatedEntity.getRealEstate().setForRentSubStatus(ForRentSubStatus.IN_PREPARATION);
    //             }

    //             realEstatePersistencePort.save(updatedEntity.getRealEstate());
    //         }
    //         case DELETE -> {
    //             SubStatus currentSubStatus = updatedEntity.getRealEstate().getSubStatus();

    //             if (currentSubStatus instanceof ForRentSubStatus) {
    //                 boolean anAdvertIsPublished = advertPersistencePort
    //                         .findByRealEstateId(oldEntity.getId())
    //                         .stream()
    //                         .anyMatch(Advert::getIsPublished);

    //                 if (anAdvertIsPublished)
    //                     oldEntity.getRealEstate().setForRentSubStatus(ForRentSubStatus.IN_NOTICE);
    //                 else
    //                     oldEntity.getRealEstate().setForRentSubStatus(ForRentSubStatus.IN_PREPARATION);

    //                 realEstatePersistencePort.save(oldEntity.getRealEstate());
    //             }
    //         }
    //     }
    }
}
