package com.padr.gys.infra.inbound.categorization.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class CategorizationRequest {
    
    @NotNull
    @NotEmpty
    protected String name;
}
