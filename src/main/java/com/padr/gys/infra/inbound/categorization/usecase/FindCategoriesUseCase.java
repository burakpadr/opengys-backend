package com.padr.gys.infra.inbound.categorization.usecase;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.categorization.entity.persistence.Category;
import com.padr.gys.domain.categorization.port.CategoryServicePort;
import com.padr.gys.infra.inbound.categorization.model.response.CategoryResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindCategoriesUseCase {

    private final CategoryServicePort categoryServicePort;

    public Page<CategoryResponse> execute(Pageable pageable) {
        Page<Category> categories = categoryServicePort.findByIsActive(true, pageable);

        List<CategoryResponse> categorizationResponses = categories.getContent().stream()
                .map(CategoryResponse::of).toList();

        return new PageImpl<>(categorizationResponses, pageable, categories.getTotalElements());
    }
}
