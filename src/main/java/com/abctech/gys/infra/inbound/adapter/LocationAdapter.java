package com.abctech.gys.infra.inbound.adapter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abctech.gys.infra.inbound.model.response.CityResponse;
import com.abctech.gys.infra.inbound.model.response.DistrictResponse;
import com.abctech.gys.infra.inbound.model.response.NeighborhoodResponse;
import com.abctech.gys.infra.inbound.usecase.FindCitiesUseCase;
import com.abctech.gys.infra.inbound.usecase.FindDistrictsUseCase;
import com.abctech.gys.infra.inbound.usecase.FindNeighborhoodsUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationAdapter {

    private FindCitiesUseCase findCitiesUseCase;
    private FindDistrictsUseCase findDistrictsUseCase;
    private FindNeighborhoodsUseCase findNeighborhoodsUseCase;

    @GetMapping("/cities")
    public List<CityResponse> getCities() {
        return findCitiesUseCase.execute();
    }

    @GetMapping("/cities/{cityName}/districts")
    public List<DistrictResponse> getDistrictByCityName(@PathVariable String cityName) {
        return findDistrictsUseCase.execute(cityName);
    }

    @GetMapping("/cities/{cityName}/districts/{districtName}/neighborhoods")
    public List<NeighborhoodResponse> getNeighborhoodsByCityNameAndDistrictName(String cityName, String districtName) {
        return findNeighborhoodsUseCase.execute(cityName, districtName);
    }
}
