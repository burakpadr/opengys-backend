package com.padr.gys.infra.inbound.categorization.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.categorization.entity.persistence.SubCategory;
import com.padr.gys.domain.categorization.port.CategoryServicePort;
import com.padr.gys.domain.categorization.port.SubCategoryServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteCategoryUseCase {

    private final CategoryServicePort categoryServicePort;
    private final SubCategoryServicePort subCategoryServicePort;

    public void execute(Long categoryId) {
        List<Long> subCategoryIds = subCategoryServicePort.findByCategoryId(categoryId).stream()
                .map(SubCategory::getId).toList();

        subCategoryServicePort.deleteAll(subCategoryIds);
        categoryServicePort.delete(categoryId);
    }
}
