package com.padr.gys.domain.frontend.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.frontend.constant.InputType;
import com.padr.gys.domain.frontend.port.InputTypeServicePort;

@Service
class InputTypeService implements InputTypeServicePort {

    @Override
    public List<InputType> getInputTypes() {
        return Arrays.asList(InputType.values());
    }

}
