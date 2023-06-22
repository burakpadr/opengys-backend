package com.abctech.gys.domain.realestate.categorization.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.abctech.gys.domain.realestate.categorization.entity.Category;

public interface CategoryServicePort {

    Page<Category> findByIsActive(Boolean isActive, Pageable Pageable);

    Category findByIdAndIsActive(Long id, Boolean isActive);

    Category create(Category category);

    Category update(Category category);

    void delete(Long id);
}
