package com.padr.gys.infra.inbound.rest.realestate.model.response;

import java.math.BigDecimal;

import com.padr.gys.domain.address.entity.Address;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {

    private BigDecimal latitude;
    private BigDecimal longitude;
    private String cityName;
    private String districtName;
    private String neighborhoodName;
    private Integer postCode;
    private String openAddress;

    public static AddressResponse of(Address address) {
        return AddressResponse.builder()
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .cityName(address.getCityName())
                .districtName(address.getDistrictName())
                .neighborhoodName(address.getNeighborhoodName())
                .postCode(address.getPostCode())
                .openAddress(address.getOpenAddress())
                .build();
    }
}
