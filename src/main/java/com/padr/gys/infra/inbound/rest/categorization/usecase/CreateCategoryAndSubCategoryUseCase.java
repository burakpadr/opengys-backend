package com.padr.gys.infra.inbound.rest.categorization.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;
import com.padr.gys.infra.inbound.rest.categorization.model.request.CreateCategoryRequest;
import com.padr.gys.infra.inbound.rest.categorization.model.response.CategoryResponse;
import com.padr.gys.infra.outbound.persistence.categorization.port.CategoryPersistencePort;
import com.padr.gys.infra.outbound.persistence.categorization.port.SubCategoryPersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateCategoryAndSubCategoryUseCase {

    private final CategoryPersistencePort categoryPersistencePort;
    private final SubCategoryPersistencePort subCategoryPersistencePort;

    public CategoryResponse execute(CreateCategoryRequest request) {
        Category category = categoryPersistencePort.save(request.to());

        List<SubCategory> subCategories = request.getSubCategoryRequests().stream().map(subCategoryRequest -> {
            SubCategory subCategory = subCategoryRequest.to();

            subCategory.setCategory(category);

            return subCategory;
        }).toList();

        category.setSubCategories(subCategoryPersistencePort.saveAll(subCategories));

        return CategoryResponse.of(category);
    }
}
