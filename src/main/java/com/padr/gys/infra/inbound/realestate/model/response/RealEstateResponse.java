package com.padr.gys.infra.inbound.realestate.model.response;

import java.util.Objects;

import com.padr.gys.domain.realestate.entity.RealEstate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RealEstateResponse {

    private Long id;
    private String no;
    private String mainStatus;
    private String subStatus;
    private String districtName;
    private String cityName;
    private String coverPhotoPath;

    public static RealEstateResponse of(RealEstate realEstate) {
        boolean coverPhotoIsNotNull = Objects.nonNull(realEstate.getCoverPhoto());

        return RealEstateResponse.builder()
                .id(realEstate.getId())
                .no(realEstate.getNo())
                .mainStatus(realEstate.getMainStatus().getValue())
                .subStatus(realEstate.getSubStatus().getValue())
                .districtName(realEstate.getAddress().getDistrictName())
                .cityName(realEstate.getAddress().getCityName())
                .coverPhotoPath(coverPhotoIsNotNull ? realEstate.getCoverPhoto().getPath() : null)
                .build();
    }
}
