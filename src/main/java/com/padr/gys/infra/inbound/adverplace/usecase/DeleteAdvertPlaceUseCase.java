package com.padr.gys.infra.inbound.adverplace.usecase;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.advertplace.port.AdvertPlaceServicePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteAdvertPlaceUseCase {
    
    private final AdvertPlaceServicePort advertPlaceServicePort;

    public void execute(Long id) {
        advertPlaceServicePort.delete(id);
    }
}
