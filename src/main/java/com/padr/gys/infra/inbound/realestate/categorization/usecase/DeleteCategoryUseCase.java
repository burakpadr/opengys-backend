package com.padr.gys.infra.inbound.realestate.categorization.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.categorization.entity.SubCategory;
import com.padr.gys.domain.realestate.categorization.port.CategoryServicePort;
import com.padr.gys.domain.realestate.categorization.port.SubCategoryServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteCategoryUseCase {

    private final CategoryServicePort categoryServicePort;
    private final SubCategoryServicePort subCategoryServicePort;

    public void execute(Long categoryId) {
        List<Long> subCategoryIds = subCategoryServicePort.findByCategoryIdAndIsActive(categoryId, true).stream()
                .map(SubCategory::getId).toList();

        subCategoryServicePort.deleteAll(subCategoryIds);
        categoryServicePort.delete(categoryId);
    }
}
