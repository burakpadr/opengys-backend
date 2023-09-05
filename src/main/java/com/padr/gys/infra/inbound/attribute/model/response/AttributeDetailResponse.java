package com.padr.gys.infra.inbound.attribute.model.response;

import java.util.List;

import com.padr.gys.domain.attribute.entity.Attribute;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AttributeDetailResponse extends AttributeResponse {

    private List<AttributeValueResponse> attributeValues;

    public static AttributeDetailResponse of(Attribute attribute) {
        return AttributeDetailResponse.builder()
                .id(attribute.getId())
                .alias(attribute.getAlias())
                .label(attribute.getLabel())
                .screenOrder(attribute.getScreenOrder())
                .attributeValues(attribute.getAttributeValues().stream().map(AttributeValueResponse::of).toList())
                .build();
    }
}
