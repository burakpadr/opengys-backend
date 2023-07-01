package com.padr.gys.infra.inbound.categorization.adapter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.categorization.model.request.CreateCategoryRequest;
import com.padr.gys.infra.inbound.categorization.model.request.UpdateCategoryRequest;
import com.padr.gys.infra.inbound.categorization.model.response.CategorizationResponse;
import com.padr.gys.infra.inbound.categorization.usecase.CreateCategoryAndSubCategoryUseCase;
import com.padr.gys.infra.inbound.categorization.usecase.DeleteCategoryUseCase;
import com.padr.gys.infra.inbound.categorization.usecase.FindCategoriesUseCase;
import com.padr.gys.infra.inbound.categorization.usecase.FindSubCategoriesUseCase;
import com.padr.gys.infra.inbound.categorization.usecase.UpdateCategoryAndSubCategoryUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategorizationAdapter {

    private final FindCategoriesUseCase findCategoriesUseCase;
    private final CreateCategoryAndSubCategoryUseCase createCategoryAndSubCategoryUseCase;
    private final FindSubCategoriesUseCase findSubCategoriesUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final UpdateCategoryAndSubCategoryUseCase updateCategoryAndSubCategoryUseCase;

    @GetMapping
    public Page<CategorizationResponse> findAllCategories(Pageable pageable) {
        return findCategoriesUseCase.execute(pageable);
    }

    @PostMapping
    public void createCategory(@RequestBody @Valid CreateCategoryRequest request) {
        createCategoryAndSubCategoryUseCase.execute(request);
    }

    @PutMapping("/{categoryId}")
    public void updateCategory(@RequestBody @Valid UpdateCategoryRequest request) {
        updateCategoryAndSubCategoryUseCase.execute(request);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        deleteCategoryUseCase.execute(categoryId);
    }

    @GetMapping("/{categoryId}/sub-categories")
    public List<CategorizationResponse> findSubCategoriesByCategoryId(@PathVariable Long categoryId) {
        return findSubCategoriesUseCase.execute(categoryId);
    }
}
