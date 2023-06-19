package com.abctech.gys.domain.core.realestate.location.port;

import java.util.List;

import io.github.burakpadr.turkeylocation4j.District;

public interface DistrictServicePort {
    
    List<District> getDisctrictByCityName(String cityName);
}
