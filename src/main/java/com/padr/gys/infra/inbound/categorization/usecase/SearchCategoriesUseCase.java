package com.padr.gys.infra.inbound.categorization.usecase;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.categorization.entity.elasticsearch.CategoryElasticsearch;
import com.padr.gys.domain.categorization.port.CategoryServicePort;
import com.padr.gys.infra.inbound.categorization.model.response.CategoryResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SearchCategoriesUseCase {

    private final CategoryServicePort categoryServicePort;

    public Page<CategoryResponse> execute(String searchTerm, Pageable pageable) {
        SearchHits<CategoryElasticsearch> categories = categoryServicePort.search(searchTerm, pageable);

        List<CategoryResponse> categoryResponses = categories.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(CategoryResponse::of).toList();

        return new PageImpl<>(categoryResponses, pageable, categories.getTotalHits());

    }
}
