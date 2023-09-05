package com.padr.gys.infra.outbound.persistence.attribute.port;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.attribute.entity.Attribute;

public interface AttributePersistencePort {
    
    Attribute save(Attribute attribute);

    Optional<Attribute> findById(Long id);

    Page<Attribute> find(Pageable pageable);

    Page<Attribute> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Attribute> findBySubCategoryId(Long subCategoryId, Pageable pageable);
}
