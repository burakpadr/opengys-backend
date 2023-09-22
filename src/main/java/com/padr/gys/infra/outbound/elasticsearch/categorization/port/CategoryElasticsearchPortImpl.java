package com.padr.gys.infra.outbound.elasticsearch.categorization.port;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.categorization.entity.elasticsearch.CategoryElasticsearch;
import com.padr.gys.infra.outbound.elasticsearch.categorization.repository.CategoryElasticsearchRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class CategoryElasticsearchPortImpl implements CategoryElasticsearchPort {

    private final CategoryElasticsearchRepository categoryElasticsearchRepository;

    @Override
    public CategoryElasticsearch save(CategoryElasticsearch categoryElasticsearch) {
        return categoryElasticsearchRepository.save(categoryElasticsearch);
    }

    @Override
    public void deleteAllByRowId(Long rowId) {
        categoryElasticsearchRepository.deleteAllByRowId(rowId);
    }

    @Override
    public CategoryElasticsearch findByRowId(Long rowId) {
        return categoryElasticsearchRepository.findByRowId(rowId);
    }
}
