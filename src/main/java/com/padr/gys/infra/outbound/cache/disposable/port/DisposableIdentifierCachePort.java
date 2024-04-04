package com.padr.gys.infra.outbound.cache.disposable.port;

import java.util.Optional;

import com.padr.gys.domain.disposable.entity.DisposableIdentifier;

public interface DisposableIdentifierCachePort {
    
    DisposableIdentifier save(DisposableIdentifier disposableIdentifier);

    Optional<DisposableIdentifier> findByUuid(String uuid);
}
