package com.padr.gys.infra.inbound.realestate.information.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.padr.gys.domain.realestate.information.entity.RealEstate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRealEstateRequest {
    
    @NotNull
    @NotEmpty
    private String no;

    @NotNull
    @Positive
    private Long categoryId;
    private Long subCategoryId;

    @NotNull
    @JsonProperty("address")
    private AddressRequest addressRequest;

    public RealEstate to() {
        return RealEstate.builder()
                .no(no)
                .address(addressRequest.to())
                .build();
    }
}
