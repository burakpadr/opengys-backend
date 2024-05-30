package com.padr.gys.infra.inbound.rest.advertplace.usecase;

import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.advertplace.entity.AdvertPlace;
import com.padr.gys.infra.inbound.rest.advertplace.model.AdvertPlaceResponse;
import com.padr.gys.infra.outbound.persistence.advertplace.port.AdvertPlacePersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAdvertPlaceByIdUseCase {

    private final AdvertPlacePersistencePort advertPlacePersistencePort;

    private final MessageSource messageSource;

    public AdvertPlaceResponse execute(Long id) {
        AdvertPlace advertPlace = advertPlacePersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("advertplace.not-found", null, LocaleContextHolder.getLocale())));

        return AdvertPlaceResponse.of(advertPlace);
    }
}
