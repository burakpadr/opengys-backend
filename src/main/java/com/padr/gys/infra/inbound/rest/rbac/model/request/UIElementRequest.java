package com.padr.gys.infra.inbound.rest.rbac.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UIElementRequest {
    
    @NotEmpty
    @NotBlank
    private String componentName;

    @NotEmpty
    private String label;
}
