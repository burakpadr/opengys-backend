package com.padr.gys.infra.inbound.categorization.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.attribute.port.AttributeServicePort;
import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.port.CategoryServicePort;
import com.padr.gys.domain.categorization.port.SubCategoryServicePort;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteCategoryUseCase {

    private final CategoryServicePort categoryServicePort;
    private final SubCategoryServicePort subCategoryServicePort;
    private final AttributeServicePort attributeServicePort;
    private final RealEstateServicePort realEstateServicePort;

    public void execute(Long categoryId) {
        Category category = categoryServicePort.findById(categoryId);

        subCategoryServicePort.deleteAll(category.getSubCategories());
        realEstateServicePort.deleteAll(category.getRealEstates());
        attributeServicePort.deleteAll(category.getAttributes());
        categoryServicePort.delete(category);
    }
}
