package com.padr.gys.infra.inbound.realestate.information.model.response;

import java.util.Objects;

import com.padr.gys.domain.realestate.information.entity.RealEstate;
import com.padr.gys.domain.realestate.status.constant.MainStatus;
import com.padr.gys.domain.realestate.status.constant.SubStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RealEstateResponse {

    private Long id;
    private String no;
    private Long categoryId;
    private Long subCategoryId;
    private MainStatus mainStatus;
    private SubStatus subStatus;
    private AddressResponse address;

    public static RealEstateResponse of(RealEstate realEstate) {
        return RealEstateResponse.builder()
                .id(realEstate.getId())
                .no(realEstate.getNo())
                .categoryId(realEstate.getCategory().getId())
                .subCategoryId(
                        Objects.nonNull(realEstate.getSubCategory()) ? realEstate.getSubCategory().getId() : null)
                .mainStatus(realEstate.getMainStatus())
                .subStatus(realEstate.getSubStatus())
                .address(AddressResponse.of(realEstate.getAddress()))
                .build();
    }
}
