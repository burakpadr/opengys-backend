package com.padr.gys.infra.inbound.rest.user.model.response;

import java.util.Objects;

import com.padr.gys.domain.user.entity.Tenant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TenantResponse {

    private Long id;
    private UserResponse user;
    private String realEstateNo;

    public static TenantResponse of(Tenant tenant) {
        boolean rentalContractIsNotNull = Objects.nonNull(tenant.getRentalContract());

        return TenantResponse.builder()
                .id(tenant.getId())
                .user(UserResponse.of(tenant.getUser()))
                .realEstateNo(rentalContractIsNotNull ? tenant.getRentalContract().getRealEstate().getNo() : "")
                .build();
    }
}
