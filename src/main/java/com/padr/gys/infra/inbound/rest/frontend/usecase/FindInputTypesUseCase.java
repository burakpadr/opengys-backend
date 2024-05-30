package com.padr.gys.infra.inbound.rest.frontend.usecase;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.frontend.constant.InputType;
import com.padr.gys.infra.inbound.rest.frontend.model.response.InputTypeResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindInputTypesUseCase {

    public List<InputTypeResponse> execute() {
        return Arrays.asList(InputType.values()).stream().map(InputTypeResponse::of).toList();
    }
}
