package com.abctech.gys.infra.inbound.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.abctech.gys.domain.realestate.location.port.DistrictServicePort;
import com.abctech.gys.infra.inbound.model.response.DistrictResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindDistrictsUseCase {

    private final DistrictServicePort districtServicePort;

    public List<DistrictResponse> execute(String cityName) {
        return districtServicePort.getDisctrictByCityName(cityName).stream().map(DistrictResponse::of).toList();
    }
}
