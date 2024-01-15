package com.padr.gys.domain.attribute.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.domain.attribute.entity.AttributeValue;
import com.padr.gys.domain.attribute.port.AttributeValueServicePort;
import com.padr.gys.infra.outbound.persistence.attribute.port.AttributeValuePersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AttributeValueService implements AttributeValueServicePort {

    private final AttributeValuePersistencePort attributeValuePersistencePort;

    @Override
    public List<AttributeValue> createAll(List<AttributeValue> attributeValues) {
        return attributeValuePersistencePort.saveAll(attributeValues);
    }

    @Override
    public void updateAll(Attribute attribute,
            List<AttributeValue> attributeValues) {
        List<AttributeValue> oldAttributeValues = attributeValuePersistencePort
                .findByAttributeId(attribute.getId());

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

        createAll(newAttributeValues);
        attributeValuePersistencePort.saveAll(oldAttributeValues);
    }

    @Override
    public void deleteAll(List<Long> attributeValueIds) {
        List<AttributeValue> attributeValues = attributeValuePersistencePort.findAllByIds(attributeValueIds);

        attributeValues.stream().parallel().forEach(attributeValue -> {
            attributeValue.setIsDeleted(true);
        });

        attributeValuePersistencePort.saveAll(attributeValues);
    }
}
