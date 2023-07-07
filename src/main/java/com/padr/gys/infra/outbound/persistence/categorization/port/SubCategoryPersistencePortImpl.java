package com.padr.gys.infra.outbound.persistence.categorization.port;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.categorization.entity.SubCategory;
import com.padr.gys.infra.outbound.persistence.categorization.repository.SubCategoryRepository;

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
