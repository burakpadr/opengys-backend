package com.padr.gys.infra.inbound.realestate.model.request;

import java.math.BigDecimal;

import com.padr.gys.domain.address.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String cityName;
    private String districtName;
    private String neighborhoodName;
    private Integer postCode;
    private String openAddress;

    public Address to() {
        return Address.builder()
                .latitude(latitude)
                .longitude(longitude)
                .cityName(cityName)
                .districtName(districtName)
                .neighborhoodName(neighborhoodName)
                .postCode(postCode)
                .openAddress(openAddress)
                .build();
    }
}
