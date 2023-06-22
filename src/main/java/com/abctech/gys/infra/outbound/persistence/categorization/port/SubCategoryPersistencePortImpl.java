package com.abctech.gys.infra.outbound.persistence.categorization.port;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.abctech.gys.domain.realestate.categorization.entity.SubCategory;
import com.abctech.gys.infra.outbound.persistence.categorization.repository.SubCategoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SubCategoryPersistencePortImpl implements SubCategoryPersistencePort {

    private final SubCategoryRepository subCategoryRepository;

    @Override
    public List<SubCategory> findByCategoryIdAndIsActive(Long categoryId, Boolean isActive) {
        return subCategoryRepository.findByCategoryIdAndIsActive(categoryId, isActive);
    }

    @Override
    public List<SubCategory> saveAll(List<SubCategory> subCategories) {
        return subCategoryRepository.saveAll(subCategories);
    }

    @Override
    public Optional<SubCategory> findByIdAndIsActive(Long id, Boolean isActive) {
        return subCategoryRepository.findByIdAndIsActive(id, isActive);
    }
}
