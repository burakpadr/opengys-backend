package com.padr.gys.domain.categorization.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;
import com.padr.gys.domain.categorization.exception.SubCategoryNotFoundException;
import com.padr.gys.domain.categorization.port.SubCategoryServicePort;
import com.padr.gys.infra.outbound.persistence.categorization.port.SubCategoryPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubCategoryService implements SubCategoryServicePort {

    private final SubCategoryPersistencePort subCategoryPersistencePort;

    @Override
    public List<SubCategory> findByCategoryIdAndIsActive(Long categoryId, Boolean isActive) {
        return subCategoryPersistencePort.findByCategoryIdAndIsActive(categoryId, isActive);
    }

    @Override
    public List<SubCategory> createAll(List<SubCategory> subCategories) {
        subCategories.stream().forEach(subCategory -> {
            subCategory.setIsActive(true);
        });

        return subCategoryPersistencePort.saveAll(subCategories);
    }

    @Override
    public void updateAll(Category category, List<SubCategory> subCategories) {
        List<SubCategory> oldSubCategories = findByCategoryIdAndIsActive(category.getId(), true);

        oldSubCategories.stream().forEach(oldSubCategory -> {
            Optional<SubCategory> subCategoryOptional = subCategories.stream()
                    .filter(s -> Objects.nonNull(s.getId()) && s.getId().equals(oldSubCategory.getId())).findAny();

            if (!subCategoryOptional.isPresent())
                oldSubCategory.setIsActive(false);
            else
                oldSubCategory.setName(subCategoryOptional.get().getName());
        });

        List<SubCategory> newSubCategories = new ArrayList<>();

        subCategories.stream().forEach(subCategory -> {
            Optional<SubCategory> subCategoryOptional = oldSubCategories.stream()
                    .filter(s -> Objects.nonNull(s.getId()) && s.getId().equals(subCategory.getId())).findAny();

            if (!subCategoryOptional.isPresent()) {
                subCategory.setCategory(category);

                newSubCategories.add(subCategory);
            }
        });

        createAll(newSubCategories);
        subCategoryPersistencePort.saveAll(oldSubCategories);
    }

    @Override
    public void deleteAll(List<Long> subCategoryIds) {
        List<SubCategory> subCategories = new ArrayList<>();

        subCategoryIds.stream().forEach(subCategoryId -> {
            SubCategory subCategory = findByIdAndIsActive(subCategoryId, true);

            subCategory.setIsActive(false);

            subCategories.add(subCategory);
        });

        subCategoryPersistencePort.saveAll(subCategories);
    }

    @Override
    public SubCategory findByIdAndIsActive(Long id, Boolean isActive) {
        return subCategoryPersistencePort.findByIdAndIsActive(id, true)
                .orElseThrow(() -> new SubCategoryNotFoundException(id));
    }
}
