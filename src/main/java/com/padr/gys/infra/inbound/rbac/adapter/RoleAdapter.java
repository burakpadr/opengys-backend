package com.padr.gys.infra.inbound.rbac.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.rbac.model.response.RoleResponse;
import com.padr.gys.infra.inbound.rbac.usecase.DeleteRoleUseCase;
import com.padr.gys.infra.inbound.rbac.usecase.FindAllRolesAsPageUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/roles")
@RequiredArgsConstructor
public class RoleAdapter {
    
    private final FindAllRolesAsPageUseCase findAllRolesAsPageUseCase;
    private final DeleteRoleUseCase deleteRoleUseCase;

    @GetMapping
    public Page<RoleResponse> findAll(Pageable pageable) {
        return findAllRolesAsPageUseCase.execute(pageable);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteRoleUseCase.execute(id);
    }
}
