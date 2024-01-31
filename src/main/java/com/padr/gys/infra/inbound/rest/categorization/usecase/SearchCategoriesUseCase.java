package com.padr.gys.infra.inbound.rest.categorization.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.categorization.port.CategoryServicePort;
import com.padr.gys.infra.inbound.rest.categorization.model.response.CategoryResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SearchCategoriesUseCase {

    private final CategoryServicePort categoryServicePort;

    public Page<CategoryResponse> execute(String searchTerm, Pageable pageable) {
        return categoryServicePort.search(searchTerm, pageable).map(CategoryResponse::of);
    }
}
