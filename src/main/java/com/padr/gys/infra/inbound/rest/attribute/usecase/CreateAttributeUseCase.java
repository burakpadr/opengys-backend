package com.padr.gys.infra.inbound.rest.attribute.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.domain.attribute.entity.AttributeValue;
import com.padr.gys.domain.attribute.port.AttributeServicePort;
import com.padr.gys.domain.attribute.port.AttributeValueServicePort;
import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.port.CategoryServicePort;
import com.padr.gys.infra.inbound.rest.attribute.model.request.CreateAttributeRequest;
import com.padr.gys.infra.inbound.rest.attribute.model.response.AttributeResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateAttributeUseCase {

    private final CategoryServicePort categoryServicePort;
    private final AttributeServicePort attributeServicePort;
    private final AttributeValueServicePort attributeValueServicePort;

    public AttributeResponse execute(CreateAttributeRequest request) {
        Category category = categoryServicePort.findById(request.getCategoryId());

        Attribute attribute = request.to();
        attribute.setCategory(category);

        attributeServicePort.create(attribute);

        switch (request.getInputType()) {
            case SELECT -> {
                List<AttributeValue> attributeValues = request.getAttributeValuesRequest().stream().parallel()
                        .map(attributeValueRequestElement -> {
                            AttributeValue attributeValue = attributeValueRequestElement.to();

                            attributeValue.setAttribute(attribute);

                            return attributeValue;
                        }).toList();

                attribute.setAttributeValues(attributeValueServicePort.createAll(attributeValues));
            }
            default -> {
                AttributeValue attributeValue = AttributeValue.builder()
                        .value("")
                        .attribute(attribute)
                        .build();

                List<AttributeValue> attributeValues = List.of(attributeValue);

                attribute.setAttributeValues(attributeValueServicePort.createAll(attributeValues));
            }
        }

        return AttributeResponse.of(attribute);
    }
}
