package com.padr.gys.infra.inbound.categorization.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.categorization.port.SubCategoryServicePort;
import com.padr.gys.infra.inbound.categorization.model.response.CategorizationResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindSubCategoriesUseCase {

    private final SubCategoryServicePort subCategoryServicePort;

    public List<CategorizationResponse> execute(Long categoryId) {
        return subCategoryServicePort.findByCategoryIdAndIsActive(categoryId, true).stream()
                .map(CategorizationResponse::of).toList();
    }
}
