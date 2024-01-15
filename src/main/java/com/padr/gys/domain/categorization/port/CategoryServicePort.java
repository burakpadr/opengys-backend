package com.padr.gys.domain.categorization.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.categorization.entity.Category;

public interface CategoryServicePort {

    Page<Category> findAll(Pageable Pageable);

    Page<Category> search(String searchTerm, Pageable pageable);

    Category findById(Long id);

    Category create(Category category);

    Category update(Category category);

    void delete(Category category);
}
