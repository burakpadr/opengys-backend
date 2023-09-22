package com.padr.gys.infra.inbound.categorization.model.response;

import com.padr.gys.domain.categorization.entity.persistence.SubCategory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubCategoryResponse {

    private Long id;
    private String name;

    public static SubCategoryResponse of(SubCategory subcCategory) {
        return SubCategoryResponse.builder()
                .id(subcCategory.getId())
                .name(subcCategory.getName())
                .build();
    }
}
