package com.padr.gys.infra.inbound.rbac.model.response;

import com.padr.gys.domain.rbac.entity.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleResponse {
    
    private Long id;
    private String label;

    public static RoleResponse of(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .label(role.getLabel())
                .build();
    }
}
