package com.padr.gys.infra.inbound.realestate.model.response;

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
        return RealEstateDetailResponse.builder()
                .id(realEstate.getId())
                .no(realEstate.getNo())
                .categoryId(realEstate.getCategory().getId())
                .subCategoryId(
                        Objects.nonNull(realEstate.getSubCategory()) ? realEstate.getSubCategory().getId() : null)
                .mainStatus(realEstate.getMainStatus().name())
                .address(AddressResponse.of(realEstate.getAddress()))
                .build();
    }
}
