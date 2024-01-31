package com.padr.gys.infra.inbound.rest.rbac.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleUIElementRequest {
    
    @NotNull
    @Valid
    @JsonProperty("role")
    private RoleRequest roleRequest;

    @NotNull
    private List<Long> uiElementIds;
}
