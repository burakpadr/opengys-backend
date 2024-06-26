package com.padr.gys.infra.inbound.rest.frontend.adapter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.padr.gys.infra.inbound.rest.frontend.model.response.InputTypeResponse;
import com.padr.gys.infra.inbound.rest.frontend.usecase.FindInputTypesUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gys/api/v1/frontend")
@RequiredArgsConstructor
public class FrontendAdapter {
    
    private final FindInputTypesUseCase findInputTypesUseCase;

    @GetMapping("/input-types")
    public List<InputTypeResponse> getInputTypes() {
        return findInputTypesUseCase.execute();
    }
}
