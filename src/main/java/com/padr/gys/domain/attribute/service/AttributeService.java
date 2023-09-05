package com.padr.gys.domain.attribute.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.domain.attribute.exception.AttributeNotFoundException;
import com.padr.gys.domain.attribute.port.AttributeServicePort;
import com.padr.gys.infra.outbound.persistence.attribute.port.AttributePersistencePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class AttributeService implements AttributeServicePort {

    private final AttributePersistencePort attributePersistencePort;

    @Override
    public Page<Attribute> find(Pageable pageable) {
        return attributePersistencePort.find(pageable);
    }

    @Override
    public Attribute findById(Long id) {
        return attributePersistencePort.findById(id).orElseThrow(AttributeNotFoundException::new);
    }

    @Override
    public Page<Attribute> findByCategoryId(Long categoryId, Pageable pageable) {
        return attributePersistencePort.findByCategoryId(categoryId, pageable);
    }

    @Override
    public Page<Attribute> findBySubCategoryId(Long subCategoryId, Pageable pageable) {
        return attributePersistencePort.findBySubCategoryId(subCategoryId, pageable);
    }

    @Override
    public Attribute create(Attribute attribute) {
        attribute.setIsActive(true);

        return attributePersistencePort.save(attribute);
    }

    @Override
    public Attribute update(Long id, Attribute attribute) {
        Attribute oldAttribute = findById(id);

        oldAttribute.setLabel(attribute.getLabel());
        oldAttribute.setScreenOrder(attribute.getScreenOrder());

        return attributePersistencePort.save(oldAttribute);
    }

    @Override
    public void delete(Long id) {
        Attribute attribute = findById(id);

        attribute.setIsActive(false);

        attributePersistencePort.save(attribute);
    }
}
