package com.padr.gys.infra.inbound.rest.attribute.model.response;

import java.util.List;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.infra.inbound.rest.categorization.model.response.CategoryResponse;
import com.padr.gys.infra.inbound.rest.categorization.model.response.SubCategoryResponse;
import com.padr.gys.infra.inbound.rest.frontend.model.response.InputTypeResponse;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AttributeResponse {

    private Long id;
    private String alias;
    private String label;
    private Integer screenOrder;
    private CategoryResponse category;
    private SubCategoryResponse subCategory;
    private InputTypeResponse inputType;
    private List<AttributeValueResponse> attributeValues;

    public static AttributeResponse of(Attribute attribute) {
        return AttributeResponse.builder()
                .id(attribute.getId())
                .alias(attribute.getAlias())
                .label(attribute.getLabel())
                .screenOrder(attribute.getScreenOrder())
                .inputType(InputTypeResponse.of(attribute.getInputType()))
                .category(CategoryResponse.of(attribute.getCategory()))
                .attributeValues(attribute.getAttributeValues().stream().map(AttributeValueResponse::of).toList())
                .build();
    }
}
