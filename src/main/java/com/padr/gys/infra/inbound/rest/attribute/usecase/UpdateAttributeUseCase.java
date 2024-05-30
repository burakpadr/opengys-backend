package com.padr.gys.infra.inbound.rest.attribute.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.domain.attribute.entity.AttributeValue;
import com.padr.gys.infra.inbound.rest.attribute.model.request.UpdateAttributeRequest;
import com.padr.gys.infra.inbound.rest.attribute.model.request.UpdateAttributeValueRequest;
import com.padr.gys.infra.outbound.persistence.attribute.port.AttributePersistencePort;
import com.padr.gys.infra.outbound.persistence.attribute.port.AttributeValuePersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateAttributeUseCase {

    private final AttributePersistencePort attributePersistencePort;
    private final AttributeValuePersistencePort attributeValuePersistencePort;

    private final MessageSource messageSource;

    public void execute(Long attributeId, UpdateAttributeRequest request) {

        // Update attribute

        Attribute attribute = attributePersistencePort.findById(attributeId)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("attribute.not-found", null, LocaleContextHolder.getLocale())));

        attribute.setLabel(request.getLabel());
        attribute.setScreenOrder(request.getScreenOrder());

        attributePersistencePort.save(attribute);

        // Update attribute values

        List<AttributeValue> oldAttributeValues = attributeValuePersistencePort
                .findByAttributeId(attribute.getId());

        List<AttributeValue> attributeValues = request.getAttributeValuesRequest().stream()
                .map(UpdateAttributeValueRequest::to).toList();

        oldAttributeValues.stream().parallel().forEach(oldAttributeValue -> {
            Optional<AttributeValue> attributeValueOptional = attributeValues.stream()
                    .filter(a -> Objects.nonNull(a.getId()) && a.getId().equals(oldAttributeValue.getId())).findAny();

            if (!attributeValueOptional.isPresent())
                oldAttributeValue.setIsDeleted(true);
            else
                oldAttributeValue.setValue(attributeValueOptional.get().getValue());
        });

        List<AttributeValue> newAttributeValues = new ArrayList<>();

        attributeValues.stream().parallel().forEach(attributeValue -> {
            Optional<AttributeValue> attributeValueOptional = oldAttributeValues.stream()
                    .filter(a -> Objects.nonNull(a.getId()) && a.getId().equals(attributeValue.getId())).findAny();

            if (!attributeValueOptional.isPresent()) {
                attributeValue.setAttribute(attribute);

                newAttributeValues.add(attributeValue);
            }
        });

        attributeValuePersistencePort.saveAll(newAttributeValues);
        attributeValuePersistencePort.saveAll(oldAttributeValues);
    }
}
