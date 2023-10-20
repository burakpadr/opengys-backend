package com.padr.gys.domain.categorization.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.categorization.entity.elasticsearch.CategoryElasticsearch;
import com.padr.gys.domain.categorization.entity.persistence.Category;
import com.padr.gys.domain.categorization.exception.CategoryNotFoundException;
import com.padr.gys.domain.categorization.port.CategoryServicePort;
import com.padr.gys.domain.common.util.ElasticsearchUtil;
import com.padr.gys.infra.outbound.elasticsearch.categorization.port.CategoryElasticsearchPort;
import com.padr.gys.infra.outbound.persistence.categorization.port.CategoryPersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServicePort {

    private final CategoryPersistencePort categoryPersistencePort;
    private final CategoryElasticsearchPort categoryElasticsearchPort;

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public Page<Category> findByIsActive(Boolean isActive, Pageable Pageable) {
        return categoryPersistencePort.findByIsActive(isActive, Pageable);
    }

    @Override
    public SearchHits<CategoryElasticsearch> search(String searchTerm, Pageable pageable) {
        return elasticsearchOperations.search(ElasticsearchUtil.prepareStringQuery(searchTerm, "name", pageable),
                CategoryElasticsearch.class,
                IndexCoordinates.of("category"));
    }

    @Override
    public Category findByIdAndIsActive(Long id, Boolean isActive) {
        return categoryPersistencePort.findByIdAndIsActive(id, isActive)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Category create(Category category) {
        category.setIsActive(true);

        categoryPersistencePort.save(category);
        categoryElasticsearchPort.save(CategoryElasticsearch.of(category));

        return category;
    }

    @Override
    public Category update(Category category) {
        Category oldCategory = findByIdAndIsActive(category.getId(), true);
        CategoryElasticsearch categoryElasticsearch = categoryElasticsearchPort.findByRowId(category.getId());

        oldCategory.setName(category.getName());
        categoryPersistencePort.save(oldCategory);

        categoryElasticsearch.updateFrom(oldCategory);
        categoryElasticsearchPort.save(categoryElasticsearch);

        return oldCategory;
    }

    @Override
    public void delete(Category category) {
        category.setIsActive(false);

        categoryPersistencePort.save(category);
        categoryElasticsearchPort.deleteAllByRowId(category.getId());
    }
}
