package com.padr.gys.infra.inbound.rest.attribute.usecase;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.domain.attribute.entity.AttributeValue;
import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.infra.inbound.rest.attribute.model.request.CreateAttributeRequest;
import com.padr.gys.infra.inbound.rest.attribute.model.response.AttributeResponse;
import com.padr.gys.infra.outbound.persistence.attribute.port.AttributePersistencePort;
import com.padr.gys.infra.outbound.persistence.attribute.port.AttributeValuePersistencePort;
import com.padr.gys.infra.outbound.persistence.categorization.port.CategoryPersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateAttributeUseCase {

    private final AttributePersistencePort attributePersistencePort;
    private final AttributeValuePersistencePort attributeValuePersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;

    private final MessageSource messageSource;

    public AttributeResponse execute(CreateAttributeRequest request) {
        Category category = categoryPersistencePort.findById(request.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException(messageSource
                        .getMessage("categorization.category.not-found", null, LocaleContextHolder.getLocale())));

        Attribute attribute = request.to();
        attribute.setCategory(category);

        attributePersistencePort.save(attribute);

        switch (request.getInputType()) {
            case SELECT -> {
                List<AttributeValue> attributeValues = request.getAttributeValuesRequest().stream().parallel()
                        .map(attributeValueRequestElement -> {
                            AttributeValue attributeValue = attributeValueRequestElement.to();

                            attributeValue.setAttribute(attribute);

                            return attributeValue;
                        }).toList();

                attribute.setAttributeValues(attributeValuePersistencePort.saveAll(attributeValues));
            }
            default -> {
                AttributeValue attributeValue = AttributeValue.builder()
                        .value("")
                        .attribute(attribute)
                        .build();

                List<AttributeValue> attributeValues = List.of(attributeValue);

                attribute.setAttributeValues(attributeValuePersistencePort.saveAll(attributeValues));
            }
        }

        return AttributeResponse.of(attribute);
    }
}
