package com.padr.gys.infra.inbound.rest.rentalcontract.usecase;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.infra.inbound.rest.rentalcontract.model.response.RentalContractResponse;

import com.padr.gys.infra.outbound.persistence.rentalcontract.port.RentalContractPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class FindRentalContractByIdUseCase {

    private final RentalContractPersistencePort rentalContractPersistencePort;

    private final MessageSource messageSource;

    public RentalContractResponse execute(Long id) {
        RentalContract rentalContract = rentalContractPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("rentalcontract.not-found", null, LocaleContextHolder.getLocale())));

        return RentalContractResponse.of(rentalContract);
    }
}
