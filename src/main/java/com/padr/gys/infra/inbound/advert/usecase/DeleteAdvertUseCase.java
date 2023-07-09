package com.padr.gys.infra.inbound.advert.usecase;

import com.padr.gys.domain.advert.port.AdvertServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteAdvertUseCase {

    private final AdvertServicePort advertServicePort;

    public void execute(Long id) {
        advertServicePort.delete(id);
    }
}
