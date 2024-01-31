package com.padr.gys.infra.inbound.rest.attribute.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.attribute.port.AttributeServicePort;
import com.padr.gys.infra.inbound.rest.attribute.model.response.AttributeResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAttributeByIdUseCase {

    private final AttributeServicePort attributeServicePort;

    public AttributeResponse execute(Long attributeId) {
        return AttributeResponse.of(attributeServicePort.findById(attributeId));
    }
}
