package com.padr.gys.infra.outbound.persistence.categorization.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.infra.outbound.persistence.categorization.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoryPersistencePortImpl implements CategoryPersistencePort {

    private final CategoryRepository categoryRepository;

    @Override
    public Page<Category> findByIsActive(Boolean isActive, Pageable pageable) {
        return categoryRepository.findByIsActive(isActive, pageable);
    }

    @Override
    public Optional<Category> findByIdAndIsActive(Long id, Boolean isActive) {
        return categoryRepository.findByIdAndIsActive(id, isActive);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

}
