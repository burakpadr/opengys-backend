package com.abctech.gys.domain.categorization.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abctech.gys.domain.categorization.entity.SubCategory;
import com.abctech.gys.domain.categorization.exception.SubCategoryNotFoundException;
import com.abctech.gys.domain.categorization.port.SubCategoryServicePort;
import com.abctech.gys.infra.outbound.persistence.categorization.port.SubCategoryPersistencePort;

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
    public List<SubCategory> updateAll(Long categoryId, List<SubCategory> subCategories) {
        List<SubCategory> oldSubCategories = findByCategoryIdAndIsActive(categoryId, true);

        oldSubCategories.stream().forEach(oldSubCategory -> {
            Optional<SubCategory> subCategoryOptional = subCategories.stream()
                    .filter(s -> s.getId().equals(oldSubCategory.getId())).findAny();

            if (!subCategoryOptional.isPresent())
                oldSubCategory.setIsActive(false);
            else
                oldSubCategory.setName(subCategoryOptional.get().getName());
        });

        subCategories.stream().forEach(subCategory -> {
            Optional<SubCategory> subCategoryOptional = oldSubCategories.stream()
                    .filter(s -> s.getId().equals(subCategory.getId())).findAny();

            if (!subCategoryOptional.isPresent())
                oldSubCategories.add(subCategory);
        });

        return subCategoryPersistencePort.saveAll(oldSubCategories);
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

    private SubCategory findByIdAndIsActive(Long id, Boolean isActive) {
        return subCategoryPersistencePort.findByIdAndIsActive(id, true)
                .orElseThrow(() -> new SubCategoryNotFoundException(id));
    }
}
