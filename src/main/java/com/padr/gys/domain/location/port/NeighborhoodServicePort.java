package com.padr.gys.domain.location.port;

import java.util.List;

public interface NeighborhoodServicePort {

    List<String> getNeighborhoodNamesByCityNameAndDistrictName(String cityName, String districtName);
}
