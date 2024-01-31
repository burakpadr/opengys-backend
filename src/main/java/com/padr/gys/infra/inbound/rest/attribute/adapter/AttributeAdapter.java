package com.padr.gys.infra.inbound.rest.attribute.adapter;

import java.util.Optional;

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

import com.padr.gys.infra.inbound.rest.attribute.model.request.CreateAttributeRequest;
import com.padr.gys.infra.inbound.rest.attribute.model.request.UpdateAttributeRequest;
import com.padr.gys.infra.inbound.rest.attribute.model.response.AttributeResponse;
import com.padr.gys.infra.inbound.rest.attribute.usecase.CreateAttributeUseCase;
import com.padr.gys.infra.inbound.rest.attribute.usecase.DeleteAttributeByIdUseCase;
import com.padr.gys.infra.inbound.rest.attribute.usecase.FindAllAttributeUseCase;
import com.padr.gys.infra.inbound.rest.attribute.usecase.FindAttributeByIdUseCase;
import com.padr.gys.infra.inbound.rest.attribute.usecase.FindAttributesByCategoryIdUseCase;
import com.padr.gys.infra.inbound.rest.attribute.usecase.UpdateAttributeUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/attributes")
@RequiredArgsConstructor
public class AttributeAdapter {

    private final CreateAttributeUseCase createAttributeUseCase;
    private final DeleteAttributeByIdUseCase deleteAttributeByIdUseCase;
    private final FindAllAttributeUseCase findAllAttributeUseCase;
    private final FindAttributesByCategoryIdUseCase findAttributesByCategoryIdUseCase;
    private final FindAttributeByIdUseCase findAttributeByIdUseCase;
    private final UpdateAttributeUseCase updateAttributeUseCase;

    @PostMapping
    public AttributeResponse create(@Valid @RequestBody CreateAttributeRequest request) {
        return createAttributeUseCase.execute(request);
    }

    @GetMapping
    public Page<AttributeResponse> find(@RequestParam("categoryId") Optional<Long> categoryIdOptional,
            @RequestParam("subCategoryId") Optional<Long> subCategoryIdOptional, Pageable pageable) {
        if (categoryIdOptional.isPresent())
            return findAttributesByCategoryIdUseCase.execute(categoryIdOptional.get(), pageable);

        return findAllAttributeUseCase.execute(pageable);
    }

    @GetMapping("/{attributeId}")
    public AttributeResponse findById(@PathVariable Long attributeId) {
        return findAttributeByIdUseCase.execute(attributeId);
    }

    @PutMapping("/{attributeId}")
    public void update(@PathVariable Long attributeId, @Valid @RequestBody UpdateAttributeRequest request) {
        updateAttributeUseCase.execute(attributeId, request);
    }

    @DeleteMapping("/{attributeId}")
    public void delete(@PathVariable Long attributeId) {
        deleteAttributeByIdUseCase.execute(attributeId);
    }
}
