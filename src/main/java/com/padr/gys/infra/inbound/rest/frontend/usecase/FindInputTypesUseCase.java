package com.padr.gys.infra.inbound.rest.frontend.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.frontend.port.InputTypeServicePort;
import com.padr.gys.infra.inbound.rest.frontend.model.response.InputTypeResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindInputTypesUseCase {

    private final InputTypeServicePort inputTypeServicePort;

    public List<InputTypeResponse> execute() {
        return inputTypeServicePort.getInputTypes().stream().map(InputTypeResponse::of).toList();
    }
}
