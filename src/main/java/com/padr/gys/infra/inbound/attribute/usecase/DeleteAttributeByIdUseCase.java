package com.padr.gys.infra.inbound.attribute.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.domain.attribute.entity.AttributeValue;
import com.padr.gys.domain.attribute.port.AttributeServicePort;
import com.padr.gys.domain.attribute.port.AttributeValueServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteAttributeByIdUseCase {

    private final AttributeServicePort attributeServicePort;
    private final AttributeValueServicePort attributeValueServicePort;

    public void execute(Long attributeId) {
        Attribute attribute = attributeServicePort.findById(attributeId);

        List<Long> attributeValueIds = attribute.getAttributeValues().stream()
                .map(AttributeValue::getId).toList();

        attributeValueServicePort.deleteAll(attributeValueIds);
        attributeServicePort.delete(attributeId);
    }
}
