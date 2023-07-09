package com.padr.gys.domain.location.port;

import java.util.List;

import io.github.burakpadr.turkeylocation4j.District;

public interface DistrictServicePort {
    
    List<District> getDisctrictByCityName(String cityName);
}