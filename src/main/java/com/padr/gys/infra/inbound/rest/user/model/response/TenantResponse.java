package com.padr.gys.infra.inbound.rest.user.model.response;

import com.padr.gys.domain.user.entity.Tenant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TenantResponse {

    private Long id;
    private UserResponse user;

    public static TenantResponse of(Tenant tenant) {
        return TenantResponse.builder()
                .id(tenant.getId())
                .user(UserResponse.of(tenant.getUser()))
                .build();
    }
}
