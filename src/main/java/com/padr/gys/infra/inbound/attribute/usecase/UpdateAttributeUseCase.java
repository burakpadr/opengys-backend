package com.padr.gys.infra.inbound.attribute.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.domain.attribute.port.AttributeServicePort;
import com.padr.gys.domain.attribute.port.AttributeValueServicePort;
import com.padr.gys.infra.inbound.attribute.model.request.UpdateAttributeRequest;
import com.padr.gys.infra.inbound.attribute.model.request.UpdateAttributeValueRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateAttributeUseCase {

    private final AttributeServicePort attributeServicePort;
    private final AttributeValueServicePort attributeValueServicePort;

    public void execute(Long attributeId, UpdateAttributeRequest request) {
        Attribute attribute = attributeServicePort.update(attributeId, request.to());

        attributeValueServicePort.updateAll(attribute,
                request.getAttributeValuesRequest().stream().map(UpdateAttributeValueRequest::to).toList());
    }
}
