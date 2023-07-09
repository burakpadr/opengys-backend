package com.padr.gys.infra.inbound.advert.model.response;

import com.padr.gys.domain.advert.entity.Advert;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class AdvertResponse {

    private Long id;
    private String advertPlaceName;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal price;
    private Boolean isPublished;

    public static AdvertResponse of(Advert advert) {
        return AdvertResponse.builder()
                .id(advert.getId())
                .advertPlaceName(advert.getAdvertPlace().getName())
                .startDate(advert.getStartDate())
                .endDate(advert.getEndDate())
                .price(advert.getPrice())
                .isPublished(advert.getIsPublished())
                .build();
    }
}
