package com.padr.gys.infra.inbound.rest.advert.usecase;

import com.padr.gys.domain.advert.port.AdvertServicePort;
import com.padr.gys.infra.inbound.rest.advert.model.response.AdvertResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAdvertByIdUseCase {

    private final AdvertServicePort advertServicePort;

    public AdvertResponse execute(Long id) {
        return AdvertResponse.of(advertServicePort.findById(id));
    }
}
