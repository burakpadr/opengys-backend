package com.padr.gys.infra.inbound.realestate.model.response;

import com.padr.gys.domain.realestate.entity.RealEstatePhoto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RealEstatePhotoResponse {

    private Long id;
    private String path;
    private Boolean isCover;

    public static RealEstatePhotoResponse of(RealEstatePhoto realEstatePhoto) {
        return RealEstatePhotoResponse.builder()
                .id(realEstatePhoto.getId())
                .path(realEstatePhoto.getPath())
                .isCover(realEstatePhoto.getIsCover())
                .build();
    }
}
