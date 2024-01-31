package com.padr.gys.infra.inbound.rest.rbac.adapter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.rest.rbac.model.response.RoleResponse;
import com.padr.gys.infra.inbound.rest.rbac.usecase.DeleteRoleUseCase;
import com.padr.gys.infra.inbound.rest.rbac.usecase.FindAllRolesAsListUseCase;
import com.padr.gys.infra.inbound.rest.rbac.usecase.FindAllRolesAsPageUseCase;
import com.padr.gys.infra.inbound.rest.rbac.usecase.FindRoleByIdUseCase;
import com.padr.gys.infra.inbound.rest.rbac.usecase.SearchRoleUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/roles")
@RequiredArgsConstructor
public class RoleAdapter {
    
    private final FindAllRolesAsPageUseCase findAllRolesAsPageUseCase;
    private final FindAllRolesAsListUseCase findAllRolesAsListUseCase;
    private final SearchRoleUseCase searchRoleUseCase;
    private final FindRoleByIdUseCase findRoleByIdUseCase;
    private final DeleteRoleUseCase deleteRoleUseCase;

    @GetMapping("/as-page")
    public Page<RoleResponse> findAll(Pageable pageable) {
        return findAllRolesAsPageUseCase.execute(pageable);
    }

    @GetMapping("/as-list")
    public List<RoleResponse> findAll() {
        return findAllRolesAsListUseCase.execute();
    }

    @GetMapping("/search")
    public Page<RoleResponse> search(@RequestParam String searchTerm, Pageable pageable) {
        return searchRoleUseCase.execute(searchTerm, pageable);
    }

    @GetMapping("/{id}")
    public RoleResponse findById(@PathVariable Long id) {
        return findRoleByIdUseCase.execute(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteRoleUseCase.execute(id);
    }
}
