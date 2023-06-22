package com.abctech.gys.infra.inbound.categorization.model.request;

import java.util.List;

import com.abctech.gys.domain.realestate.categorization.entity.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateCategoryRequest extends CategorizationRequest {
    
    @JsonProperty("subCategories")
    private List<CreateSubCategoryRequest> subCategoryRequests;

    public Category to() {
        return Category.builder()
                .name(name)
                .build();
    }
}
