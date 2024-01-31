package com.padr.gys.infra.inbound.rest.categorization.model.response;

import java.util.List;

import com.padr.gys.domain.categorization.entity.Category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {

    private Long id;
    private String name;
    private List<SubCategoryResponse> subCategories;

    public static CategoryResponse of(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .subCategories(category.getSubCategories().stream().map(SubCategoryResponse::of).toList())
                .build();
    }
}
