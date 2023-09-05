package com.padr.gys.infra.inbound.attribute.model.request;

import java.util.List;

import com.padr.gys.domain.attribute.entity.Attribute;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAttributeRequest {

    @NotNull
    @NotEmpty
    private String label;

    @NotNull
    private Integer screenOrder;
    private List<UpdateAttributeValueRequest> attributeValuesRequest;

    public Attribute to() {
        return Attribute.builder()
                .label(label)
                .screenOrder(screenOrder)
                .build();
    }
}
