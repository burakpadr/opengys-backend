package com.abctech.gys.infra.inbound.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.abctech.gys.domain.realestate.location.port.CityServicePort;
import com.abctech.gys.infra.inbound.model.response.CityResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindCitiesUseCase {

    private final CityServicePort cityServicePort;

    public List<CityResponse> execute() {
        return cityServicePort.getCities().stream().map(CityResponse::of).toList();
    }
}
