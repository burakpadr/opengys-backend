package com.padr.gys.domain.statusmanager.configuration;

import com.padr.gys.domain.statusmanager.reporter.StatusChangeReporter;
import com.padr.gys.infra.outbound.persistence.advert.port.AdvertPersistencePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StatusChangeReporterConfiguration {

    private final RealEstatePersistencePort realEstatePersistencePort;
    private final AdvertPersistencePort advertPersistencePort;

    @Bean
    public StatusChangeReporter statusChangeReporter() {
        return new StatusChangeReporter(realEstatePersistencePort, advertPersistencePort);
    }
}
