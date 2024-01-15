package com.padr.gys.infra.inbound.categorization.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;
import com.padr.gys.domain.categorization.port.CategoryServicePort;
import com.padr.gys.domain.categorization.port.SubCategoryServicePort;
import com.padr.gys.infra.inbound.categorization.model.request.UpdateCategoryRequest;
import com.padr.gys.infra.inbound.categorization.model.request.UpdateSubCategoryRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateCategoryAndSubCategoryUseCase {

    private final CategoryServicePort categoryServicePort;
    private final SubCategoryServicePort subCategoryServicePort;

    public void execute(Long categoryId, UpdateCategoryRequest request) {
        Category oldCategory = categoryServicePort.findById(categoryId);

        Category updatedCategory = request.to();
        updatedCategory.setId(categoryId);

        categoryServicePort.update(updatedCategory);

        List<SubCategory> subCategories = request.getSubCategoryRequests().stream().map(UpdateSubCategoryRequest::to)
                .toList();

        subCategoryServicePort.updateAll(oldCategory, subCategories);
    }
}
