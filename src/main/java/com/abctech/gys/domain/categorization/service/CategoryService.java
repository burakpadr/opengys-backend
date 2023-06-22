package com.abctech.gys.domain.categorization.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.abctech.gys.domain.categorization.entity.Category;
import com.abctech.gys.domain.categorization.exception.CategoryNotFoundException;
import com.abctech.gys.domain.categorization.port.CategoryServicePort;
import com.abctech.gys.infra.outbound.persistence.categorization.port.CategoryPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServicePort {
    
    private final CategoryPersistencePort categoryPersistencePort;

    @Override
    public Page<Category> findByIsActive(Boolean isActive, Pageable Pageable) {
        return categoryPersistencePort.findByIsActive(isActive, Pageable);
    }

    @Override
    public Category findByIdAndIsActive(Long id, Boolean isActive) {
        return categoryPersistencePort.findByIdAndIsActive(id, isActive).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Category create(Category category) {
        category.setIsActive(true);

        return categoryPersistencePort.save(category);
    }

    @Override
    public Category update(Category category) {
        Category oldCategory = findByIdAndIsActive(category.getId(), true);

        oldCategory.setName(category.getName());

        return categoryPersistencePort.save(oldCategory);
    }

    @Override
    public void delete(Long id) {
        Category category = findByIdAndIsActive(id, true);

        category.setIsActive(false);

        categoryPersistencePort.save(category);
    }

}
