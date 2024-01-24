package com.padr.gys.domain.categorization.service;

import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.port.CategoryServicePort;
import com.padr.gys.infra.outbound.persistence.categorization.port.CategoryPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServicePort {

    private final CategoryPersistencePort categoryPersistencePort;

    private final MessageSource messageSource;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryPersistencePort.findAll(pageable);
    }

    @Override
    public Page<Category> search(String searchTerm, Pageable pageable) {
        return categoryPersistencePort.findBySearchTerm(searchTerm, pageable);
    }

    @Override
    public Category findById(Long id) {
        return categoryPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(messageSource
                        .getMessage("categorization.category.not-found", null, LocaleContextHolder.getLocale())));
    }

    @Override
    public Category create(Category category) {
        categoryPersistencePort.save(category);

        return category;
    }

    @Override
    public Category update(Category category) {
        Category oldCategory = findById(category.getId());

        oldCategory.setName(category.getName());
        categoryPersistencePort.save(oldCategory);

        return oldCategory;
    }

    @Override
    public void delete(Category category) {
        category.setIsDeleted(true);

        categoryPersistencePort.save(category);
    }
}
