package com.padr.gys.infra.inbound.attribute.model.request;

import com.padr.gys.domain.attribute.entity.AttributeValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAttributeValueRequest {

    private String value;

    public AttributeValue to() {
        return AttributeValue.builder()
                .value(value)
                .build();
    }
}
