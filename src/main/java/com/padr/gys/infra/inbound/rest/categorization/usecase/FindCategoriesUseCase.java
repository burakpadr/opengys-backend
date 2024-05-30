package com.padr.gys.infra.inbound.rest.categorization.usecase;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.infra.inbound.rest.categorization.model.response.CategoryResponse;
import com.padr.gys.infra.outbound.persistence.categorization.port.CategoryPersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindCategoriesUseCase {

    private final CategoryPersistencePort categoryPersistencePort;

    public Page<CategoryResponse> execute(Pageable pageable) {
        Page<Category> categories = categoryPersistencePort.findAll(pageable);

        List<CategoryResponse> categorizationResponses = categories.getContent().stream()
                .map(CategoryResponse::of).toList();

        return new PageImpl<>(categorizationResponses, pageable, categories.getTotalElements());
    }
}
