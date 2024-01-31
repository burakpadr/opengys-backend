package com.padr.gys.infra.inbound.rest.advert.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.padr.gys.domain.advert.entity.Advert;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class AdvertResponse {

    private Long id;
    private Long advertPlaceId;
    private String advertPlaceName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;
    private BigDecimal price;
    private Boolean isPublished;

    public static AdvertResponse of(Advert advert) {
        return AdvertResponse.builder()
                .id(advert.getId())
                .advertPlaceId(advert.getAdvertPlace().getId())
                .advertPlaceName(advert.getAdvertPlace().getName())
                .startDate(advert.getStartDate())
                .endDate(advert.getEndDate())
                .price(advert.getPrice())
                .isPublished(advert.getIsPublished())
                .build();
    }
}
