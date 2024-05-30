package com.padr.gys.infra.inbound.rest.realestate.usecase;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.realestate.model.response.RealEstateDetailResponse;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class FindRealEstateByIdUseCase {

    private final RealEstatePersistencePort realEstatePersistencePort;

    private final MessageSource messageSource;

    public RealEstateDetailResponse execute(Long id) {
        RealEstate realEstate = realEstatePersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("realestate.not-found", null, LocaleContextHolder.getLocale())));

        return RealEstateDetailResponse.of(realEstate);
    }
}
