package com.abctech.gys.infra.inbound.categorization.model.request;

import java.util.List;

import com.abctech.gys.domain.categorization.entity.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UpdateCategoryRequest extends CategorizationRequest {
    
    @NotNull
    @Positive
    private Long id;

    @JsonProperty("subCategories")
    private List<UpdateSubCategoryRequest> subCategoryRequests;

    public Category to() {
        return Category.builder()
                .id(id)
                .name(name)
                .build();
    }
}
