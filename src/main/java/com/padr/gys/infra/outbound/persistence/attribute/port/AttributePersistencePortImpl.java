package com.padr.gys.infra.outbound.persistence.attribute.port;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.infra.outbound.persistence.attribute.repository.AttributeRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class AttributePersistencePortImpl implements AttributePersistencePort {

    private final AttributeRepository attributeRepository;

    @Override
    public Attribute save(Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    @Override
    public List<Attribute> saveAll(List<Attribute> attributes) {
        return attributeRepository.saveAll(attributes);
    }

    @Override
    public Page<Attribute> find(Pageable pageable) {
        return attributeRepository.findAll(pageable);
    }

    @Override
    public Optional<Attribute> findById(Long id) {
        return attributeRepository.findById(id);
    }

    @Override
    public Page<Attribute> findByCategoryId(Long categoryId, Pageable pageable) {
        return attributeRepository.findByCategoryIdOrderByScreenOrderAsc(categoryId, pageable);
    }
}
