package com.padr.gys.infra.inbound.categorization.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.padr.gys.domain.categorization.entity.persistence.Category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryRequest {

    @NotNull
    @NotEmpty
    private String name;

    @JsonProperty("subCategories")
    private List<UpdateSubCategoryRequest> subCategoryRequests;

    public Category to() {
        return Category.builder()
                .name(name)
                .build();
    }
}
