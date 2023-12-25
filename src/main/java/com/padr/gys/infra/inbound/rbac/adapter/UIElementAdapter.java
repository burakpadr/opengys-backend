package com.padr.gys.infra.inbound.rbac.adapter;

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

import com.padr.gys.infra.inbound.rbac.model.request.UIElementRequest;
import com.padr.gys.infra.inbound.rbac.model.response.UIElementResponse;
import com.padr.gys.infra.inbound.rbac.usecase.CreateUIElementUseCase;
import com.padr.gys.infra.inbound.rbac.usecase.DeleteUIElementUseCase;
import com.padr.gys.infra.inbound.rbac.usecase.FindAllUIElementsAsPageUseCase;
import com.padr.gys.infra.inbound.rbac.usecase.FindUIElementByIdUseCase;
import com.padr.gys.infra.inbound.rbac.usecase.UpdateUIElementUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/ui-elements")
@RequiredArgsConstructor
public class UIElementAdapter {

    private final CreateUIElementUseCase createUIElementUseCase;
    private final FindAllUIElementsAsPageUseCase findAllUIElementsAsPageUseCase;
    private final FindUIElementByIdUseCase findUIElementByIdUseCase;
    private final UpdateUIElementUseCase updateUIElementUseCase;
    private final DeleteUIElementUseCase deleteUIElementUseCase;
    
    @PostMapping
    public UIElementResponse create(@Valid @RequestBody UIElementRequest request) {
        return createUIElementUseCase.execute(request);
    }

    @GetMapping
    public Page<UIElementResponse> findAll(Pageable pageable) {
        return findAllUIElementsAsPageUseCase.execute(pageable);
    }

    @GetMapping("/{id}")
    public UIElementResponse findById(@PathVariable Long id) {
        return findUIElementByIdUseCase.execute(id);
    }

    @PutMapping("/{id}")
    public UIElementResponse update(@PathVariable Long id, @Valid @RequestBody UIElementRequest request) {
        return updateUIElementUseCase.execute(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteUIElementUseCase.execute(id);
    }
}
