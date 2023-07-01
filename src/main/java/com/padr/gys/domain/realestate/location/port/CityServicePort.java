package com.padr.gys.domain.realestate.location.port;

import java.util.List;

import io.github.burakpadr.turkeylocation4j.City;

public interface CityServicePort {
    
    List<City> getCities();
}
