package com.padr.gys.infra.inbound.rest.categorization.usecase;

import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.infra.inbound.rest.categorization.model.response.CategoryResponse;
import com.padr.gys.infra.outbound.persistence.categorization.port.CategoryPersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindCategoryByIdUseCase {

    private final CategoryPersistencePort categoryPersistencePort;
    
    private final MessageSource messageSource;

    public CategoryResponse execute(Long id) {
        Category category = categoryPersistencePort.findById(id)
                .orElseThrow(() -> new NoSuchElementException(messageSource
                        .getMessage("categorization.category.not-found", null, LocaleContextHolder.getLocale())));

        return CategoryResponse.of(category);
    }
}
