package com.padr.gys.infra.inbound.rest.attribute.usecase;

import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.infra.inbound.rest.attribute.model.response.AttributeResponse;
import com.padr.gys.infra.outbound.persistence.attribute.port.AttributePersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAttributeByIdUseCase {

    private final AttributePersistencePort attributePersistencePort;

    private final MessageSource messageSource;

    public AttributeResponse execute(Long attributeId) {
        Attribute attribute = attributePersistencePort.findById(attributeId)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("attribute.not-found", null, LocaleContextHolder.getLocale())));

        return AttributeResponse.of(attribute);
    }
}
