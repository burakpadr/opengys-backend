package com.abctech.gys.infra.inbound.categorization.model.response;

import com.abctech.gys.domain.realestate.categorization.entity.Category;
import com.abctech.gys.domain.realestate.categorization.entity.SubCategory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategorizationResponse {

    private Long id;
    private String name;

    public static CategorizationResponse of(Category category) {
        return CategorizationResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static CategorizationResponse of(SubCategory subcCategory) {
        return CategorizationResponse.builder()
                .id(subcCategory.getId())
                .name(subcCategory.getName())
                .build();
    }
}
