package com.padr.gys.infra.inbound.rbac.adapter;

import java.util.List;

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
import com.padr.gys.infra.inbound.rbac.usecase.FindAllUIElementsUseCase;
import com.padr.gys.infra.inbound.rbac.usecase.FindAllowedComponentsToBeSeenUseCase;
import com.padr.gys.infra.inbound.rbac.usecase.FindUIElementByIdUseCase;
import com.padr.gys.infra.inbound.rbac.usecase.UpdateUIElementUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/ui-elements")
@RequiredArgsConstructor
public class UIElementAdapter {

    private final CreateUIElementUseCase createUIElementUseCase;
    private final FindAllUIElementsUseCase findAllUIElementsAsPageUseCase;
    private final FindUIElementByIdUseCase findUIElementByIdUseCase;
    private final FindAllowedComponentsToBeSeenUseCase findAllowedComponentsToBeSeenUseCase;
    private final UpdateUIElementUseCase updateUIElementUseCase;
    private final DeleteUIElementUseCase deleteUIElementUseCase;
    
    @PostMapping
    public List<UIElementResponse> createAll(@Valid @RequestBody List<UIElementRequest> request) {
        return createUIElementUseCase.execute(request);
    }

    @GetMapping
    public List<UIElementResponse> findAll() {
        return findAllUIElementsAsPageUseCase.execute();
    }

    @GetMapping("/allowed-components-to-be-seen")
    public List<UIElementResponse> allowedComponentsToBeSeen() {
        return findAllowedComponentsToBeSeenUseCase.execute();
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
