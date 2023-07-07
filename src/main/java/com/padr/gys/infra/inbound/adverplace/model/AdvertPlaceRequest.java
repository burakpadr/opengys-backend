package com.padr.gys.infra.inbound.adverplace.model;

import com.padr.gys.domain.advertplace.entity.AdvertPlace;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertPlaceRequest {
    
    @NotNull
    @NotEmpty
    private String name;

    public AdvertPlace to() {
        return AdvertPlace.builder()
                .name(name)
                .build();
    }
}
