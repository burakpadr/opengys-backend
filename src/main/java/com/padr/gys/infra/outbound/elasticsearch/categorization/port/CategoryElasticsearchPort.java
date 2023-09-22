package com.padr.gys.infra.outbound.elasticsearch.categorization.port;

import com.padr.gys.domain.categorization.entity.elasticsearch.CategoryElasticsearch;

public interface CategoryElasticsearchPort {
    
CategoryElasticsearch save(CategoryElasticsearch categoryElasticsearch);

    void deleteAllByRowId(Long rowId);

    CategoryElasticsearch findByRowId(Long rowId);
}
