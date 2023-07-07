package com.padr.gys.domain.location.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.location.port.DistrictServicePort;

import io.github.burakpadr.turkeylocation4j.District;

@Service
public class DistrictService implements DistrictServicePort {

    @Override
    public List<District> getDisctrictByCityName(String cityName) {
        return District.fromCityName(cityName);
    }
    
}
