package com.padr.gys.infra.inbound.attribute.model.response;

import com.padr.gys.domain.attribute.entity.Attribute;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AttributeResponse {

    private Long id;
    private String alias;
    private String label;
    private Integer screenOrder;

    public static AttributeResponse of(Attribute attribute) {
        return AttributeResponse.builder()
                .id(attribute.getId())
                .alias(attribute.getAlias())
                .label(attribute.getLabel())
                .screenOrder(attribute.getScreenOrder())
                .build();
    }
}
