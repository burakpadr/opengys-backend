package com.padr.gys.infra.inbound.categorization.model.request;

import com.padr.gys.domain.realestate.categorization.entity.SubCategory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UpdateSubCategoryRequest extends CategorizationRequest {

    private Long id;

    public SubCategory to() {
        return SubCategory.builder()
                .id(id)
                .name(name)
                .build();
    }
}
