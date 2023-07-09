package com.padr.gys.infra.inbound.advert.model.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.padr.gys.domain.advert.entity.Advert;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdvertRequest {

    @NotNull
    @Positive
    private Long advertPlaceId;

    @NotNull
    private LocalDate startDate;
    private LocalDate endDate;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Boolean isPublished;

    @NotNull
    @Positive
    private Long realEstateId;

    public Advert to() {
        return Advert.builder()
                .startDate(startDate)
                .endDate(endDate)
                .price(price)
                .isPublished(isPublished)
                .build();
    }
}
