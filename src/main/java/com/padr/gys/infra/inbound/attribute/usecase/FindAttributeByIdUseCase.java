package com.padr.gys.infra.inbound.attribute.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.attribute.port.AttributeServicePort;
import com.padr.gys.infra.inbound.attribute.model.response.AttributeDetailResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAttributeByIdUseCase {

    private final AttributeServicePort attributeServicePort;

    public AttributeDetailResponse execute(Long attributeId) {
        return AttributeDetailResponse.of(attributeServicePort.findById(attributeId));
    }
}
