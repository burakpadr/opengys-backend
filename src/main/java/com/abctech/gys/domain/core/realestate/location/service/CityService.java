package com.abctech.gys.domain.core.realestate.location.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abctech.gys.domain.core.realestate.location.port.CityServicePort;

import io.github.burakpadr.turkeylocation4j.City;

@Service
public class CityService implements CityServicePort {

    @Override
    public List<City> getCities() {
        return City.fromNoFilter();
    }
    
}
