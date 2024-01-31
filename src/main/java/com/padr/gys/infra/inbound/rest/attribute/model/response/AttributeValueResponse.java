package com.padr.gys.infra.inbound.rest.attribute.model.response;

import com.padr.gys.domain.attribute.entity.AttributeValue;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttributeValueResponse {

    private Long id;
    private String value;

    public static AttributeValueResponse of(AttributeValue attributeValue) {
        return AttributeValueResponse.builder()
                .id(attributeValue.getId())
                .value(attributeValue.getValue())
                .build();
    }
}
