package com.padr.gys.infra.outbound.client.tcmb.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TcmbEndpoint {

    KURLAR_TODAY("/today.xml");

    private final String endpoint;
}
