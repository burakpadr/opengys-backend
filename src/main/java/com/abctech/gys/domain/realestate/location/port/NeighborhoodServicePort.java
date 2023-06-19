package com.abctech.gys.domain.realestate.location.port;

import java.util.List;

import io.github.burakpadr.turkeylocation4j.Neighborhood;

public interface NeighborhoodServicePort {

    List<Neighborhood> getNeighborhoodsByCityNameAndDistrictName(String cityName, String districtName);
}
