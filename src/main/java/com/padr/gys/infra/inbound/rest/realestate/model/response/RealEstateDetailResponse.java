package com.padr.gys.infra.inbound.rest.realestate.model.response;

import java.util.Objects;

import com.padr.gys.domain.realestate.entity.RealEstate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RealEstateDetailResponse {

    private Long id;
    private String no;
    private Long categoryId;
    private Long subCategoryId;
    private String mainStatus;
    private AddressResponse address;

    public static RealEstateDetailResponse of(RealEstate realEstate) {
        boolean categoryIsNotNull = Objects.nonNull(realEstate.getCategory());
        boolean subCategoryIsNotNull = Objects.nonNull(realEstate.getSubCategory());

        return RealEstateDetailResponse.builder()
                .id(realEstate.getId())
                .no(realEstate.getNo())
                .categoryId(categoryIsNotNull ? realEstate.getCategory().getId() : null)
                .subCategoryId(subCategoryIsNotNull ? realEstate.getSubCategory().getId() : null)
                .mainStatus(realEstate.getMainStatus().name())
                .address(AddressResponse.of(realEstate.getAddress()))
                .build();
    }
}
