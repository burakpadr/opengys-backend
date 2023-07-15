package com.padr.gys.domain.statusmanager.handler;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.status.constant.ForRentSubStatus;
import com.padr.gys.domain.status.constant.SubStatus;
import com.padr.gys.domain.statusmanager.model.StatusChangeReportModel;
import com.padr.gys.infra.outbound.persistence.advert.port.AdvertPersistencePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdvertStatusChangeHandler extends StatusChangeHandler<StatusChangeReportModel<Advert>> {

    private final RealEstatePersistencePort realEstatePersistencePort;
    private final AdvertPersistencePort advertPersistencePort;

    @Override
    public void onNext(StatusChangeReportModel<Advert> item) {
        switch (item.getType()) {
            case CREATE -> {
                SubStatus currentSubStatus = item.getOldEntity().getRealEstate().getSubStatus();

                if (currentSubStatus instanceof  ForRentSubStatus)
                    if (ForRentSubStatus.IN_NOTICE.getOrder() > currentSubStatus.getOrder())
                        item.getOldEntity().getRealEstate().setForRentSubStatus(ForRentSubStatus.IN_NOTICE);

                realEstatePersistencePort.save(item.getOldEntity().getRealEstate());
            }
            case UPDATE, DELETE -> {
                SubStatus currentSubStatus = item.getOldEntity().getRealEstate().getSubStatus();

                if (currentSubStatus instanceof ForRentSubStatus) {
                    boolean anAdvertIsPublished = advertPersistencePort.
                            findByRealEstateIdAndIsActive(item.getOldEntity().getId(), true)
                            .stream()
                            .anyMatch(Advert::getIsPublished);

                    if (currentSubStatus.equals(ForRentSubStatus.IN_NOTICE) && !anAdvertIsPublished)
                        item.getOldEntity().getRealEstate().setForRentSubStatus(ForRentSubStatus.IN_PREPARATION);
                }

                realEstatePersistencePort.save(item.getOldEntity().getRealEstate());
            }
        }
    }
}
