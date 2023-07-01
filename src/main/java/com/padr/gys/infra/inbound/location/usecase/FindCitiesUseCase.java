package com.padr.gys.infra.inbound.location.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.location.port.CityServicePort;
import com.padr.gys.infra.inbound.location.model.response.CityResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindCitiesUseCase {

    private final CityServicePort cityServicePort;

    public List<CityResponse> execute() {
        return cityServicePort.getCities().stream().map(CityResponse::of).toList();
    }
}
