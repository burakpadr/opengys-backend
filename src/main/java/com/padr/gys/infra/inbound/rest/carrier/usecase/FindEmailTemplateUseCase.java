package com.padr.gys.infra.inbound.rest.carrier.usecase;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.carrier.port.EmailTemplateServicePort;
import com.padr.gys.infra.inbound.rest.carrier.model.response.EmailTemplateResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindEmailTemplateUseCase {

    private final EmailTemplateServicePort emailTemplateServicePort;

    private final MessageSource messageSource;

    public EmailTemplateResponse execute(Optional<Long> idOptional, Optional<String> codeOptional) {
        if (idOptional.isPresent())
            return EmailTemplateResponse.of(emailTemplateServicePort.findById(idOptional.get()));
        else if (codeOptional.isPresent())
            return EmailTemplateResponse.of(emailTemplateServicePort.findByCode(codeOptional.get()));
        else
            throw new NoSuchElementException(messageSource.getMessage("carrier.email-template.not-found", null,
                    LocaleContextHolder.getLocale()));
    }
}
