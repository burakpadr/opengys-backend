package com.padr.gys.infra.inbound.attribute.model.request;

import com.padr.gys.domain.attribute.entity.AttributeValue;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAttributeValueRequest {

    private Long id;
    private String value;

    public AttributeValue to() {
        return AttributeValue.builder()
                .id(id)
                .value(value)
                .build();
    }
}
