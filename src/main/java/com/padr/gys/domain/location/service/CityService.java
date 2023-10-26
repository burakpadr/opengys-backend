package com.padr.gys.domain.location.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.common.util.YamlUtil;
import com.padr.gys.domain.location.constant.LocationConstant;
import com.padr.gys.domain.location.port.CityServicePort;

@Service
public class CityService implements CityServicePort {

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getCityNames() {
        List<Map<String, Object>> content = (List<Map<String, Object>>) YamlUtil
                .yamlToJson(LocationConstant.CITY_FILE_PATH);

        return content.stream()
                .map(cityMap -> {
                    return (String) cityMap.get("name");
                }).toList();
    }
}
