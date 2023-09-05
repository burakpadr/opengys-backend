package com.padr.gys.domain.attribute.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.padr.gys.domain.attribute.entity.Attribute;

public interface AttributeServicePort {

    Attribute findById(Long id);

    Page<Attribute> find(Pageable pageable);

    Page<Attribute> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Attribute> findBySubCategoryId(Long subCategoryId, Pageable pageable);

    Attribute create(Attribute attribute);

    Attribute update(Long id, Attribute attribute);

    void delete(Long id);
}
