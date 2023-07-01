package com.padr.gys.infra.inbound.realestate.location.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.location.port.DistrictServicePort;
import com.padr.gys.infra.inbound.realestate.location.model.response.DistrictResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindDistrictsUseCase {

    private final DistrictServicePort districtServicePort;

    public List<DistrictResponse> execute(String cityName) {
        return districtServicePort.getDisctrictByCityName(cityName).stream().map(DistrictResponse::of).toList();
    }
}