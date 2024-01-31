package com.padr.gys.infra.inbound.rest.rbac.adapter;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.rest.rbac.model.request.RoleUIElementRequest;
import com.padr.gys.infra.inbound.rest.rbac.model.response.RoleUIElementResponse;
import com.padr.gys.infra.inbound.rest.rbac.usecase.CanSeeUIElementUseCase;
import com.padr.gys.infra.inbound.rest.rbac.usecase.CreateRoleUIElementUseCase;
import com.padr.gys.infra.inbound.rest.rbac.usecase.FindRoleUIElementByRoleIdUseCase;
import com.padr.gys.infra.inbound.rest.rbac.usecase.UpdateRoleUIElementUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/role-ui-elements")
@RequiredArgsConstructor
public class RoleUIElementAdapter {

    private final FindRoleUIElementByRoleIdUseCase findRoleUIElementByRoleIdUseCase;
    private final CreateRoleUIElementUseCase createRoleUIElementUseCase;
    private final UpdateRoleUIElementUseCase updateRoleUIElementUseCase;
    private final CanSeeUIElementUseCase canSeeUIElementUseCase;

    @PostMapping
    public List<RoleUIElementResponse> create(@Valid @RequestBody RoleUIElementRequest request) {
        return createRoleUIElementUseCase.execute(request);
    }

    @GetMapping
    public List<RoleUIElementResponse> findByRoleId(@RequestParam Long roleId) {
        return findRoleUIElementByRoleIdUseCase.execute(roleId);
    }

    @GetMapping("/can-see-component")
    public boolean canSeeComponent(@RequestParam String componentName) {
        return canSeeUIElementUseCase.execute(componentName);
    }

    @PutMapping
    public List<RoleUIElementResponse> update(@RequestParam Long roleId, @Validated @RequestBody RoleUIElementRequest request) {
        return updateRoleUIElementUseCase.execute(roleId, request);
    }
}
