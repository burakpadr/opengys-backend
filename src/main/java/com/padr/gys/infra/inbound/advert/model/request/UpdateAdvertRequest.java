package com.padr.gys.infra.inbound.advert.model.request;

import com.padr.gys.domain.advert.entity.Advert;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdvertRequest {

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

    public Advert to() {
        return Advert.builder()
                .startDate(startDate)
                .endDate(endDate)
                .price(price)
                .isPublished(isPublished)
                .build();
    }
}
