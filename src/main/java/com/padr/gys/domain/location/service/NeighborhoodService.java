package com.padr.gys.domain.location.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.common.util.StringUtil;
import com.padr.gys.domain.common.util.YamlUtil;
import com.padr.gys.domain.location.constant.LocationConstant;
import com.padr.gys.domain.location.port.NeighborhoodServicePort;

@Service
public class NeighborhoodService implements NeighborhoodServicePort {

    @Override
    @SuppressWarnings("unchecked")  
    public List<String> getNeighborhoodNamesByCityNameAndDistrictName(String cityName, String districtName) {
        Map<String, Object> content = (Map<String, Object>) YamlUtil
                .yamlToJson(LocationConstant.DISTRICTS_DIR_PATH.concat("/")
                        .concat(StringUtil.clearTurkishChars(cityName).toLowerCase()).concat(".yaml"));

        List<String> neigborhoods = new ArrayList<>();

        content.entrySet().stream().forEach(entry -> {
            String districtNameOfEntry = StringUtil.clearTurkishChars(entry.getKey()).toLowerCase();

            if (StringUtil.clearTurkishChars(districtName).toLowerCase().equals(districtNameOfEntry)) {
                ((Map<String, Object>) entry.getValue()).entrySet().stream().forEach(entry2 -> {
                    List<String> neighborhoodNames = (List<String>) ((Map<String, Object>) entry2.getValue())
                            .get("neighborhoods");

                    neigborhoods.addAll(neighborhoodNames);
                });
            }
        });

        return neigborhoods;
    }
}
