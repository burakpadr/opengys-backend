package com.padr.gys.infra.inbound.realestate.model.response;

import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.infra.inbound.status.model.response.StatusResponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RealEstateDetailResponse {
    
    private Long id;
    private String no;
    private StatusResponse mainStatus;
    private AddressResponse address;

    public static RealEstateDetailResponse of(RealEstate realEstate) {
        return RealEstateDetailResponse.builder()
                .id(realEstate.getId())
                .no(realEstate.getNo())
                .mainStatus(StatusResponse.of(realEstate.getMainStatus()))
                .address(AddressResponse.of(realEstate.getAddress()))
                .build();
    }
}
