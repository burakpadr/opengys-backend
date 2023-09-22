package com.padr.gys.infra.outbound.elasticsearch.categorization.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.categorization.entity.elasticsearch.CategoryElasticsearch;

@Repository
public interface CategoryElasticsearchRepository extends ElasticsearchRepository<CategoryElasticsearch, String> {

    void deleteAllByRowId(Long rowId);

    CategoryElasticsearch findByRowId(Long rowId);
}
