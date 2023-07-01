package com.padr.gys.domain.realestate.location.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.realestate.location.port.NeighborhoodServicePort;

import io.github.burakpadr.turkeylocation4j.Neighborhood;

@Service
public class NeighborhoodService implements NeighborhoodServicePort {

    @Override
    public List<Neighborhood> getNeighborhoodsByCityNameAndDistrictName(String cityName, String districtName) {
        return Neighborhood.fromCityNameAndDistrictName(cityName, districtName);
    }
    
}
