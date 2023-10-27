package com.padr.gys.domain.location.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.common.util.StringUtil;
import com.padr.gys.domain.common.util.YamlUtil;
import com.padr.gys.domain.location.constant.LocationConstant;
import com.padr.gys.domain.location.port.DistrictServicePort;

@Service
public class DistrictService implements DistrictServicePort {

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getDistrictNamesByCityName(String cityName) {
        Map<String, Object> content = (Map<String, Object>) YamlUtil
                .yamlToJson(LocationConstant.DISTRICTS_DIR_PATH.concat("/")
                        .concat(StringUtil.clearTurkishChars(cityName).toLowerCase()).concat(".yaml"));

        return content.entrySet().stream()
                .map(entry -> {
                    return entry.getKey();
                }).toList();
    }
}
