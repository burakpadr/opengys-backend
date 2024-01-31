package com.padr.gys.infra.inbound.rest.attribute.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.domain.frontend.constant.InputType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAttributeRequest {

    @NotNull
    @NotEmpty
    private String alias;

    @NotNull
    @NotEmpty
    private String label;

    @NotNull
    private Integer screenOrder;

    @NotNull
    private InputType inputType;

    @NotNull
    @Positive
    private Long categoryId;

    @JsonProperty(value = "attributeValues")
    private List<CreateAttributeValueRequest> attributeValuesRequest;

    public Attribute to() {
        return Attribute.builder()
                .alias(alias)
                .label(label)
                .screenOrder(screenOrder)
                .inputType(inputType)
                .build();
    }
}
