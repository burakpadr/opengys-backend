package com.padr.gys.infra.outbound.cache.disposable.port;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.disposable.entity.DisposableIdentifier;
import com.padr.gys.infra.outbound.cache.disposable.repository.DisposableIdentifierRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class DisposableIdentifierCachePortImpl implements DisposableIdentifierCachePort {
    
    private final DisposableIdentifierRepository disposableIndentifierRepository;

    @Override
    public Optional<DisposableIdentifier> findByUuid(String uuid) {
        return disposableIndentifierRepository.findByUuid(uuid);
    }

    @Override
    public DisposableIdentifier save(DisposableIdentifier disposableIdentifier) {
        return disposableIndentifierRepository.save(disposableIdentifier);
    }
}
