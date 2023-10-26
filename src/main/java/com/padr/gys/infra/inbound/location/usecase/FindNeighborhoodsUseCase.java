package com.padr.gys.infra.inbound.location.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.location.port.NeighborhoodServicePort;
import com.padr.gys.infra.inbound.location.model.response.NeighborhoodResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindNeighborhoodsUseCase {

    private final NeighborhoodServicePort neighborhoodServicePort;

    public List<NeighborhoodResponse> execute(String cityName, String districtName) {
        return neighborhoodServicePort.getNeighborhoodNamesByCityNameAndDistrictName(cityName, districtName).stream()
                .map(NeighborhoodResponse::of).toList();
    }
}
