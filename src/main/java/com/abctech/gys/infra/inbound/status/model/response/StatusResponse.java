package com.abctech.gys.infra.inbound.status.model.response;

import com.abctech.gys.domain.realestate.status.constant.MainStatus;

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
