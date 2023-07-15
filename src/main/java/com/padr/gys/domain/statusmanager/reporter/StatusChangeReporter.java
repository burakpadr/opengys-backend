package com.padr.gys.domain.statusmanager.reporter;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.domain.statusmanager.handler.AdvertStatusChangeHandler;
import com.padr.gys.domain.statusmanager.handler.RealEstateStatusChangeHandler;
import com.padr.gys.domain.statusmanager.handler.RentalContractStatusChangeHandler;
import com.padr.gys.domain.statusmanager.model.StatusChangeReportModel;
import com.padr.gys.infra.outbound.persistence.advert.port.AdvertPersistencePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import com.padr.gys.infra.outbound.persistence.rentalcontract.port.RentalContractPersistencePort;
import lombok.Getter;

import java.util.concurrent.SubmissionPublisher;

@Getter
public class StatusChangeReporter {

    private final RealEstatePersistencePort realEstatePersistencePort;
    private final AdvertPersistencePort advertPersistencePort;

    private final SubmissionPublisher<StatusChangeReportModel<RealEstate>> createRealEstateReporter;
    private final SubmissionPublisher<StatusChangeReportModel<Advert>> createAdvertReporter;
    private final SubmissionPublisher<StatusChangeReportModel<Advert>> updateAdvertReporter;
    private final SubmissionPublisher<StatusChangeReportModel<RentalContract>> createRentalContractReporter;
    private final SubmissionPublisher<StatusChangeReportModel<RentalContract>> deleteRentalContractReporter;

    public StatusChangeReporter(RealEstatePersistencePort realEstatePersistencePort,
                                AdvertPersistencePort advertPersistencePort) {
        this.realEstatePersistencePort = realEstatePersistencePort;
        this.advertPersistencePort = advertPersistencePort;

        createRealEstateReporter = new SubmissionPublisher<>();
        createAdvertReporter = new SubmissionPublisher<>();
        updateAdvertReporter = new SubmissionPublisher<>();
        createRentalContractReporter = new SubmissionPublisher<>();
        deleteRentalContractReporter = new SubmissionPublisher<>();

        RealEstateStatusChangeHandler realEstateStatusChangeHandler = new
                RealEstateStatusChangeHandler(realEstatePersistencePort);
        AdvertStatusChangeHandler advertStatusChangeHandler = new AdvertStatusChangeHandler(realEstatePersistencePort,
                advertPersistencePort);
        RentalContractStatusChangeHandler rentalContractStatusChangeHandler = new RentalContractStatusChangeHandler(
                realEstatePersistencePort, advertPersistencePort);

        createRealEstateReporter.subscribe(realEstateStatusChangeHandler);
        createAdvertReporter.subscribe(advertStatusChangeHandler);
        updateAdvertReporter.subscribe(advertStatusChangeHandler);
        createRentalContractReporter.subscribe(rentalContractStatusChangeHandler);
        deleteRentalContractReporter.subscribe(rentalContractStatusChangeHandler);;
    }
}
