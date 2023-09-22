package com.padr.gys.domain.categorization.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;

import com.padr.gys.domain.categorization.entity.elasticsearch.CategoryElasticsearch;
import com.padr.gys.domain.categorization.entity.persistence.Category;

public interface CategoryServicePort {

    Page<Category> findByIsActive(Boolean isActive, Pageable Pageable);

    SearchHits<CategoryElasticsearch> search(String searchTerm, Pageable pageable);

    Category findByIdAndIsActive(Long id, Boolean isActive);

    Category create(Category category);

    Category update(Category category);

    void delete(Long id);
}
