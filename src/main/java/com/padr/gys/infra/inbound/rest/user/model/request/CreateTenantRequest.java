package com.padr.gys.infra.inbound.rest.user.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTenantRequest {
    
    @NotNull
    @Valid
    private CreateUserRequest user;
}
