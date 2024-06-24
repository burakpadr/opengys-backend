package com.padr.gys.infra.inbound.rest.realestate.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.status.constant.MainStatus;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRealEstateRequest {
    
    @NotNull
    @NotEmpty
    private String no;

    @NotNull
    @Positive
    private Long categoryId;
    private Long subCategoryId;

    private MainStatus mainStatus = MainStatus.FOR_RENT;

    @NotNull
    @JsonProperty("address")
    @Valid
    private AddressRequest addressRequest;

    public RealEstate to() {
        return RealEstate.builder()
                .no(no)
                .mainStatus(mainStatus)
                .build();
    }
}
