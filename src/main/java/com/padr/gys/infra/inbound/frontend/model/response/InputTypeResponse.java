package com.padr.gys.infra.inbound.frontend.model.response;

import com.padr.gys.domain.frontend.constant.InputType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InputTypeResponse {

    private String alias;

    public static InputTypeResponse of(InputType inputType) {
        return InputTypeResponse.builder()
                .alias(inputType.name())
                .build();
    }
}
