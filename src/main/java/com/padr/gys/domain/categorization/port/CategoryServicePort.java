package com.padr.gys.domain.categorization.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.categorization.entity.Category;

public interface CategoryServicePort {

    Page<Category> findByIsActive(Boolean isActive, Pageable Pageable);

    Page<Category> search(String searchTerm, Pageable pageable);

    Category findByIdAndIsActive(Long id, Boolean isActive);

    Category create(Category category);

    Category update(Category category);

    void delete(Category category);
}
