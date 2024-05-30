package com.padr.gys.infra.inbound.rest.attribute.usecase;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.domain.attribute.entity.AttributeValue;
import com.padr.gys.infra.outbound.persistence.attribute.port.AttributePersistencePort;
import com.padr.gys.infra.outbound.persistence.attribute.port.AttributeValuePersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteAttributeByIdUseCase {

    private final AttributePersistencePort attributePersistencePort;
    private final AttributeValuePersistencePort attributeValuePersistencePort;

    private final MessageSource messageSource;

    public void execute(Long attributeId) {
        Attribute attribute = attributePersistencePort.findById(attributeId)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("attribute.not-found", null, LocaleContextHolder.getLocale())));

        List<Long> attributeValueIds = attribute.getAttributeValues().stream()
                .map(AttributeValue::getId).toList();

        deleteAttributeValues(attributeValueIds);

        attribute.setIsDeleted(true);
        attributePersistencePort.save(attribute);
    }

    public void deleteAttributeValues(List<Long> attributeValueIds) {
        List<AttributeValue> attributeValues = attributeValuePersistencePort.findAllByIds(attributeValueIds);

        attributeValues.stream().parallel().forEach(attributeValue -> {
            attributeValue.setIsDeleted(true);
        });

        attributeValuePersistencePort.saveAll(attributeValues);
    }
}
