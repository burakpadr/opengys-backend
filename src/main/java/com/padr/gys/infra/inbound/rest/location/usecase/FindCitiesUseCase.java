package com.padr.gys.infra.inbound.rest.location.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.location.port.CityServicePort;
import com.padr.gys.infra.inbound.rest.location.model.response.CityResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindCitiesUseCase {

    private final CityServicePort cityServicePort;

    public List<CityResponse> execute() {
        return cityServicePort.getCityNames().stream().map(CityResponse::of).toList();
    }
}
