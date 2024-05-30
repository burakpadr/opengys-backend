package com.padr.gys.infra.inbound.rest.categorization.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.infra.inbound.rest.categorization.model.response.CategoryResponse;
import com.padr.gys.infra.outbound.persistence.categorization.port.CategoryPersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SearchCategoriesUseCase {

    private final CategoryPersistencePort categoryPersistencePort;

    public Page<CategoryResponse> execute(String searchTerm, Pageable pageable) {
        return categoryPersistencePort.findBySearchTerm(searchTerm, pageable).map(CategoryResponse::of);
    }
}
