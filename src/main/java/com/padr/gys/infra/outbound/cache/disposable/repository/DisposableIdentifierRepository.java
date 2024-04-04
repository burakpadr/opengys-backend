package com.padr.gys.infra.outbound.cache.disposable.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.disposable.entity.DisposableIdentifier;

@Repository
public interface DisposableIdentifierRepository extends CrudRepository<DisposableIdentifier, String> {
    
    Optional<DisposableIdentifier> findByUuid(String uuid);
}
