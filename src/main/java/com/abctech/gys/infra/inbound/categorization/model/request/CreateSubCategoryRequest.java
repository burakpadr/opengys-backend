package com.abctech.gys.infra.inbound.categorization.model.request;

import com.abctech.gys.domain.categorization.entity.SubCategory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateSubCategoryRequest extends CategorizationRequest {
    
    public SubCategory to() {
        return SubCategory.builder()
                .name(name)
                .build();
    }
}
