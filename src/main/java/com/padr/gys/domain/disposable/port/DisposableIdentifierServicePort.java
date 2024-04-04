package com.padr.gys.domain.disposable.port;

import java.util.Optional;

import com.padr.gys.domain.disposable.entity.DisposableIdentifier;

public interface DisposableIdentifierServicePort {
    
    DisposableIdentifier save(DisposableIdentifier disposableIdentifier);

    Optional<DisposableIdentifier> findByUuid(String findByUuid);
}
