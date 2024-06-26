package com.padr.gys.infra.inbound.rest.location.adapter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.rest.location.model.response.CityResponse;
import com.padr.gys.infra.inbound.rest.location.model.response.DistrictResponse;
import com.padr.gys.infra.inbound.rest.location.model.response.NeighborhoodResponse;
import com.padr.gys.infra.inbound.rest.location.usecase.FindCitiesUseCase;
import com.padr.gys.infra.inbound.rest.location.usecase.FindDistrictsUseCase;
import com.padr.gys.infra.inbound.rest.location.usecase.FindNeighborhoodsUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/location")
@RequiredArgsConstructor
public class LocationAdapter {

    private final FindCitiesUseCase findCitiesUseCase;
    private final FindDistrictsUseCase findDistrictsUseCase;
    private final FindNeighborhoodsUseCase findNeighborhoodsUseCase;

    @GetMapping("/cities")
    public List<CityResponse> getCities() {
        return findCitiesUseCase.execute();
    }

    @GetMapping("/cities/{cityName}/districts")
    public List<DistrictResponse> getDistrictByCityName(@PathVariable String cityName) {
        return findDistrictsUseCase.execute(cityName);
    }

    @GetMapping("/cities/{cityName}/districts/{districtName}/neighborhoods")
    public List<NeighborhoodResponse> getNeighborhoodsByCityNameAndDistrictName(@PathVariable String cityName, @PathVariable String districtName) {
        return findNeighborhoodsUseCase.execute(cityName, districtName);
    }
}
