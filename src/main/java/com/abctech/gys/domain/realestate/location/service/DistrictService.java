package com.abctech.gys.domain.realestate.location.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abctech.gys.domain.realestate.location.port.DistrictServicePort;

import io.github.burakpadr.turkeylocation4j.District;

@Service
public class DistrictService implements DistrictServicePort {

    @Override
    public List<District> getDisctrictByCityName(String cityName) {
        return District.fromCityName(cityName);
    }
    
}
