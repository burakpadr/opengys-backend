package com.padr.gys.infra.inbound.rest.categorization.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.rest.categorization.model.request.CreateCategoryRequest;
import com.padr.gys.infra.inbound.rest.categorization.model.request.UpdateCategoryRequest;
import com.padr.gys.infra.inbound.rest.categorization.model.response.CategoryResponse;
import com.padr.gys.infra.inbound.rest.categorization.usecase.CreateCategoryAndSubCategoryUseCase;
import com.padr.gys.infra.inbound.rest.categorization.usecase.DeleteCategoryUseCase;
import com.padr.gys.infra.inbound.rest.categorization.usecase.FindCategoriesUseCase;
import com.padr.gys.infra.inbound.rest.categorization.usecase.FindCategoryByIdUseCase;
import com.padr.gys.infra.inbound.rest.categorization.usecase.SearchCategoriesUseCase;
import com.padr.gys.infra.inbound.rest.categorization.usecase.UpdateCategoryAndSubCategoryUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/categories")
@RequiredArgsConstructor
public class CategorizationAdapter {

    private final FindCategoriesUseCase findCategoriesUseCase;
    private final FindCategoryByIdUseCase findCategoryByIdUseCase;
    private final CreateCategoryAndSubCategoryUseCase createCategoryAndSubCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final UpdateCategoryAndSubCategoryUseCase updateCategoryAndSubCategoryUseCase;
    private final SearchCategoriesUseCase searchCategoriesUseCase;

    @GetMapping
    public Page<CategoryResponse> findAllCategories(Pageable pageable) {
        return findCategoriesUseCase.execute(pageable);
    }

    @GetMapping("/search")
    public Page<CategoryResponse> search(@RequestParam("search") String searchTerm, Pageable pageable) {
        return searchCategoriesUseCase.execute(searchTerm, pageable);
    }

    @GetMapping("/{categoryId}")
    public CategoryResponse findById(@PathVariable Long categoryId) {
        return findCategoryByIdUseCase.execute(categoryId);
    }

    @PostMapping
    public CategoryResponse createCategory(@RequestBody @Valid CreateCategoryRequest request) {
        return createCategoryAndSubCategoryUseCase.execute(request);
    }

    @PutMapping("/{categoryId}")
    public void updateCategory(@PathVariable Long categoryId, @RequestBody @Valid UpdateCategoryRequest request) {
        updateCategoryAndSubCategoryUseCase.execute(categoryId, request);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        deleteCategoryUseCase.execute(categoryId);
    }
}
