package com.padr.gys.infra.inbound.rest.rbac.model.request;

import com.padr.gys.domain.rbac.entity.Role;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {
    
    @NotEmpty
    private String label;

    public Role to() {
        return Role.builder()
                .label(label)
                .build();
    }
}
