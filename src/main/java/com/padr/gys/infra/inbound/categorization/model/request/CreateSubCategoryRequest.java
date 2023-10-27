package com.padr.gys.infra.inbound.categorization.model.request;

import com.padr.gys.domain.categorization.entity.SubCategory;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSubCategoryRequest {

    @NotNull
    @NotEmpty
    private String name;

    public SubCategory to() {
        return SubCategory.builder()
                .name(name)
                .build();
    }
}
