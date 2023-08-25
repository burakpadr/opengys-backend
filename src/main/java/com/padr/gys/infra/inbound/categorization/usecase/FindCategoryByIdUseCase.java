package com.padr.gys.infra.inbound.categorization.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.categorization.port.CategoryServicePort;
import com.padr.gys.infra.inbound.categorization.model.response.CategoryResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindCategoryByIdUseCase {
    
    private final CategoryServicePort categoryServicePort;

    public CategoryResponse execute(Long id) {
        return CategoryResponse.of(categoryServicePort.findByIdAndIsActive(id, true));
    }
}
