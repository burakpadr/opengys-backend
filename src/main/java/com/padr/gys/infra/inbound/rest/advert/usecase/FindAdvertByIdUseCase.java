package com.padr.gys.infra.inbound.rest.advert.usecase;

import com.padr.gys.domain.advert.entity.Advert;
import com.padr.gys.infra.inbound.rest.advert.model.response.AdvertResponse;
import com.padr.gys.infra.outbound.persistence.advert.port.AdvertPersistencePort;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAdvertByIdUseCase {

    private final AdvertPersistencePort advertPersistencePort;

    private final MessageSource messageSource;

    public AdvertResponse execute(Long id) {
        Advert advert = advertPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("advert.not-found", null, LocaleContextHolder.getLocale())));

        return AdvertResponse.of(advert);
    }
}
