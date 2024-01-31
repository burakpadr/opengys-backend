package com.padr.gys.infra.inbound.rest.status.model.response;

import com.padr.gys.domain.status.constant.MainStatus;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatusResponse {

    private String alias;
    private String value;

    public static StatusResponse of(MainStatus mainStatus) {
        return StatusResponse.builder()
                .alias(mainStatus.name())
                .value(mainStatus.getValue())
                .build();
    }
}
