package com.abctech.gys.infra.inbound.status.model.response;

import com.abctech.gys.domain.realestate.status.constant.SubStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SubStatusResponse extends StatusResponse {

    private Integer order;

    public static SubStatusResponse of(SubStatus subStatus) {
        return SubStatusResponse.builder()
                .alias(subStatus.getAlias())
                .value(subStatus.getValue())
                .order(subStatus.getOrder())
                .build();
    }
}
