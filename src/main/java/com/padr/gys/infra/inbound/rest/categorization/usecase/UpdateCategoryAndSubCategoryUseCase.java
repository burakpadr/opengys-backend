package com.padr.gys.infra.inbound.rest.categorization.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;
import com.padr.gys.infra.inbound.rest.categorization.model.request.UpdateCategoryRequest;
import com.padr.gys.infra.inbound.rest.categorization.model.request.UpdateSubCategoryRequest;
import com.padr.gys.infra.outbound.persistence.categorization.port.CategoryPersistencePort;
import com.padr.gys.infra.outbound.persistence.categorization.port.SubCategoryPersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateCategoryAndSubCategoryUseCase {

    private final CategoryPersistencePort categoryPersistencePort;
    private final SubCategoryPersistencePort subCategoryPersistencePort;

    private final MessageSource messageSource;

    public void execute(Long categoryId, UpdateCategoryRequest request) {
        Category category = categoryPersistencePort.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException(messageSource
                        .getMessage("categorization.category.not-found", null, LocaleContextHolder.getLocale())));

        // Update category

        category.setName(request.getName());

        categoryPersistencePort.save(category);

        // Update sub categories

        List<SubCategory> oldSubCategories = subCategoryPersistencePort.findByCategoryId(categoryId);

        List<SubCategory> requestedSubCategories = request.getSubCategoryRequests().stream()
                .map(UpdateSubCategoryRequest::to)
                .toList();

        oldSubCategories.stream().forEach(oldSubCategory -> {
            Optional<SubCategory> subCategoryOptional = requestedSubCategories.stream()
                    .filter(s -> Objects.nonNull(s.getId()) && s.getId().equals(oldSubCategory.getId())).findAny();

            if (!subCategoryOptional.isPresent())
                oldSubCategory.setIsDeleted(true);
            else
                oldSubCategory.setName(subCategoryOptional.get().getName());
        });

        List<SubCategory> newSubCategories = new ArrayList<>();

        requestedSubCategories.stream().forEach(subCategory -> {
            Optional<SubCategory> subCategoryOptional = oldSubCategories.stream()
                    .filter(s -> Objects.nonNull(s.getId()) && s.getId().equals(subCategory.getId())).findAny();

            if (!subCategoryOptional.isPresent()) {
                subCategory.setCategory(category);

                newSubCategories.add(subCategory);
            }
        });

        subCategoryPersistencePort.saveAll(newSubCategories);
        subCategoryPersistencePort.saveAll(oldSubCategories);
    }
}
