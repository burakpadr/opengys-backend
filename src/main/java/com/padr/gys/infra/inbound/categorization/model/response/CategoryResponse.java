package com.padr.gys.infra.inbound.categorization.model.response;

import java.util.List;

import com.padr.gys.domain.categorization.entity.elasticsearch.CategoryElasticsearch;
import com.padr.gys.domain.categorization.entity.persistence.Category;

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

    public static CategoryResponse of(CategoryElasticsearch categoryElasticsearch) {
        return CategoryResponse.builder()
                .id(categoryElasticsearch.getRowId())
                .name(categoryElasticsearch.getName())
                .build();
    }
}
