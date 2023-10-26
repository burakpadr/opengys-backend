package com.padr.gys.domain.location.port;

import java.util.List;

public interface DistrictServicePort {
    
    List<String> getDistrictNamesByCityName(String cityName);
}
