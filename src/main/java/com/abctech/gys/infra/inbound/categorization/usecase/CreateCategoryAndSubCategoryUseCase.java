package com.abctech.gys.infra.inbound.categorization.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.abctech.gys.domain.realestate.categorization.entity.Category;
import com.abctech.gys.domain.realestate.categorization.entity.SubCategory;
import com.abctech.gys.domain.realestate.categorization.port.CategoryServicePort;
import com.abctech.gys.domain.realestate.categorization.port.SubCategoryServicePort;
import com.abctech.gys.infra.inbound.categorization.model.request.CreateCategoryRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateCategoryAndSubCategoryUseCase {

    private final CategoryServicePort categoryServicePort;
    private final SubCategoryServicePort subCategoryServicePort;

    public void execute(CreateCategoryRequest request) {
        Category category = categoryServicePort.create(request.to());

        List<SubCategory> subCategories = request.getSubCategoryRequests().stream().map(subCategoryRequest -> {
            SubCategory subCategory = subCategoryRequest.to();

            subCategory.setCategory(category);

            return subCategory;
        }).toList();

        subCategoryServicePort.createAll(subCategories);
    }
}
