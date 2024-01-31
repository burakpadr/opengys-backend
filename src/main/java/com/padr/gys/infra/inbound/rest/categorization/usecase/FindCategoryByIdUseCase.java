package com.padr.gys.infra.inbound.rest.categorization.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.categorization.port.CategoryServicePort;
import com.padr.gys.infra.inbound.rest.categorization.model.response.CategoryResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindCategoryByIdUseCase {
    
    private final CategoryServicePort categoryServicePort;

    public CategoryResponse execute(Long id) {
        return CategoryResponse.of(categoryServicePort.findById(id));
    }
}
