package com.padr.gys.infra.inbound.realestate.model.request;

import com.padr.gys.domain.realestate.entity.RealEstatePhoto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealEstatePhotoRequest {

    private Long id;

    @NotNull
    @NotEmpty
    private String contentBase64;

    @NotNull
    @NotEmpty
    private String extension;

    @NotNull
    private Boolean isCover;

    public RealEstatePhoto to() {
        return RealEstatePhoto.builder()
                .extension(extension)
                .contentBase64(contentBase64)
                .isCover(isCover)
                .build();
    }
}
