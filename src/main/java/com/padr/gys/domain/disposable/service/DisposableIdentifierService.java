package com.padr.gys.domain.disposable.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.padr.gys.domain.disposable.entity.DisposableIdentifier;
import com.padr.gys.domain.disposable.port.DisposableIdentifierServicePort;
import com.padr.gys.infra.outbound.cache.disposable.port.DisposableIdentifierCachePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class DisposableIdentifierService implements DisposableIdentifierServicePort {
    
    private final DisposableIdentifierCachePort disposableIdentifierCachePort;

    @Override
    public DisposableIdentifier save(DisposableIdentifier disposableIdentifier) {
        return disposableIdentifierCachePort.save(disposableIdentifier);
    }

    @Override
    public Optional<DisposableIdentifier> findByUuid(String findByUuid) {
        return disposableIdentifierCachePort.findByUuid(findByUuid);
    }
}
