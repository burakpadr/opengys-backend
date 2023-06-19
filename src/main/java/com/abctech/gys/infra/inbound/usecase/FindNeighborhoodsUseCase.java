package com.abctech.gys.infra.inbound.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.abctech.gys.domain.realestate.location.port.NeighborhoodServicePort;
import com.abctech.gys.infra.inbound.model.response.NeighborhoodResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindNeighborhoodsUseCase {

    private final NeighborhoodServicePort neighborhoodServicePort;

    public List<NeighborhoodResponse> execute(String cityName, String districtName) {
        return neighborhoodServicePort.getNeighborhoodsByCityNameAndDistrictName(cityName, districtName).stream()
                .map(NeighborhoodResponse::of).toList();
    }
}
