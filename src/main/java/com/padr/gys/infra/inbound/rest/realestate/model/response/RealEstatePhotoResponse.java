package com.padr.gys.infra.inbound.rest.realestate.model.response;

import com.padr.gys.domain.realestate.entity.RealEstatePhoto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RealEstatePhotoResponse {
 
    private Long id;
    private String path;

    public static RealEstatePhotoResponse of(RealEstatePhoto realEstatePhoto) {
        return RealEstatePhotoResponse.builder()
                .id(realEstatePhoto.getId())
                .path(realEstatePhoto.getPath())
                .build();
    }
}
