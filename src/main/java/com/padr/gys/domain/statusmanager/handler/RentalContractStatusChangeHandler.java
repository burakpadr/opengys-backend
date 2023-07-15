package com.padr.gys.domain.statusmanager.handler;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.status.constant.ForRentSubStatus;
import com.padr.gys.domain.status.constant.SubStatus;
import com.padr.gys.domain.statusmanager.model.StatusChangeReportModel;
import com.padr.gys.infra.outbound.persistence.advert.port.AdvertPersistencePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RentalContractStatusChangeHandler extends StatusChangeHandler<StatusChangeReportModel<RentalContract>> {

    private final RealEstatePersistencePort realEstatePersistencePort;
    private final AdvertPersistencePort advertPersistencePort;

    @Override
    public void onNext(StatusChangeReportModel<RentalContract> item) {
        switch (item.getType()) {
            case CREATE -> {
                SubStatus currentSubStatus = item.getOldEntity().getRealEstate().getSubStatus();

                if (currentSubStatus instanceof ForRentSubStatus)
                    if (ForRentSubStatus.RENTED.getOrder() > currentSubStatus.getOrder())
                        item.getOldEntity().getRealEstate().setForRentSubStatus(ForRentSubStatus.RENTED);

                realEstatePersistencePort.save(item.getOldEntity().getRealEstate());
            }
            case UPDATE -> {
                SubStatus currentSubStatus = item.getOldEntity().getRealEstate().getSubStatus();

                if (currentSubStatus instanceof ForRentSubStatus) {
                    if (item.getUpdatedEntity().getIsPublished())
                        item.getUpdatedEntity().getRealEstate().setForRentSubStatus(ForRentSubStatus.RENTED);
                    else
                        item.getUpdatedEntity().getRealEstate().setForRentSubStatus(ForRentSubStatus.IN_NOTICE);
                }

                realEstatePersistencePort.save(item.getUpdatedEntity().getRealEstate());
            }
            case DELETE -> {
                SubStatus currentSubStatus = item.getOldEntity().getRealEstate().getSubStatus();

                if (currentSubStatus instanceof  ForRentSubStatus) {
                    boolean anAdvertIsPublished = advertPersistencePort.
                            findByRealEstateIdAndIsActive(item.getOldEntity().getId(), true)
                            .stream()
                            .anyMatch(Advert::getIsPublished);

                    if (anAdvertIsPublished)
                        item.getOldEntity().getRealEstate().setForRentSubStatus(ForRentSubStatus.IN_NOTICE);
                    else
                        item.getOldEntity().getRealEstate().setForRentSubStatus(ForRentSubStatus.IN_PREPARATION);

                    realEstatePersistencePort.save(item.getOldEntity().getRealEstate());
                }
            }
        }
    }
}
