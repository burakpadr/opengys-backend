package com.padr.gys.domain.categorization.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.categorization.constant.CategorizationExceptionMessage;
import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;
import com.padr.gys.domain.categorization.port.SubCategoryServicePort;
import com.padr.gys.infra.outbound.persistence.categorization.port.SubCategoryPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubCategoryService implements SubCategoryServicePort {

    private final SubCategoryPersistencePort subCategoryPersistencePort;

    @Override
    public List<SubCategory> findByCategoryId(Long categoryId) {
        return subCategoryPersistencePort.findByCategoryId(categoryId);
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
        List<SubCategory> oldSubCategories = findByCategoryId(category.getId());

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
    public void deleteAll(List<SubCategory> subCategories) {
        subCategories.stream().forEach(subCategory -> {
            subCategory.setIsActive(false);
        });

        subCategoryPersistencePort.saveAll(subCategories);
    }

    @Override
    public SubCategory findById(Long id) {
        return subCategoryPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(CategorizationExceptionMessage.SUBCATEGORY_NOT_FOUND));
    }
}
