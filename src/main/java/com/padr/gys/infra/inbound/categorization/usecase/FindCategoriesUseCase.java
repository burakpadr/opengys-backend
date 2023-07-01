package com.padr.gys.infra.inbound.categorization.usecase;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.categorization.entity.Category;
import com.padr.gys.domain.realestate.categorization.port.CategoryServicePort;
import com.padr.gys.infra.inbound.categorization.model.response.CategorizationResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindCategoriesUseCase {

    private final CategoryServicePort categoryServicePort;

    public Page<CategorizationResponse> execute(Pageable pageable) {
        Page<Category> categories = categoryServicePort.findByIsActive(true, pageable);

        List<CategorizationResponse> categorizationResponses = categories.getContent().stream()
                .map(CategorizationResponse::of).toList();

        return new PageImpl<>(categorizationResponses, pageable, categories.getTotalElements());
    }
}
