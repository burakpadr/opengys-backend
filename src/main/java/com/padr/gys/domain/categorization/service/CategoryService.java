package com.padr.gys.domain.categorization.service;

import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.categorization.constant.CategorizationExceptionMessage;
import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.port.CategoryServicePort;
import com.padr.gys.infra.outbound.persistence.categorization.port.CategoryPersistencePort;

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
    public Page<Category> search(String searchTerm, Pageable pageable) {
        return categoryPersistencePort.findBySearchTerm(searchTerm, pageable);
    }

    @Override
    public Category findByIdAndIsActive(Long id, Boolean isActive) {
        return categoryPersistencePort.findByIdAndIsActive(id, isActive)
                .orElseThrow(() -> new NoSuchElementException(CategorizationExceptionMessage.CATEGORY_NOT_FOUND));
    }

    @Override
    public Category create(Category category) {
        category.setIsActive(true);

        categoryPersistencePort.save(category);

        return category;
    }

    @Override
    public Category update(Category category) {
        Category oldCategory = findByIdAndIsActive(category.getId(), true);

        oldCategory.setName(category.getName());
        categoryPersistencePort.save(oldCategory);

        return oldCategory;
    }

    @Override
    public void delete(Category category) {
        category.setIsActive(false);

        categoryPersistencePort.save(category);
    }
}
