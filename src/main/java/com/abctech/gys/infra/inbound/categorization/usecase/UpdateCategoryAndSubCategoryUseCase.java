package com.abctech.gys.infra.inbound.categorization.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.abctech.gys.domain.categorization.entity.SubCategory;
import com.abctech.gys.domain.categorization.port.CategoryServicePort;
import com.abctech.gys.domain.categorization.port.SubCategoryServicePort;
import com.abctech.gys.infra.inbound.categorization.model.request.UpdateCategoryRequest;
import com.abctech.gys.infra.inbound.categorization.model.request.UpdateSubCategoryRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateCategoryAndSubCategoryUseCase {

    private final CategoryServicePort categoryServicePort;
    private final SubCategoryServicePort subCategoryServicePort;

    public void execute(UpdateCategoryRequest request) {
        categoryServicePort.update(request.to());

        List<SubCategory> subCategories = request.getSubCategoryRequests().stream().map(UpdateSubCategoryRequest::to)
                .toList();

        subCategoryServicePort.updateAll(request.getId(), subCategories);
    }
}
