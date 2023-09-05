package com.padr.gys.infra.outbound.persistence.attribute.port;

import java.util.List;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.attribute.entity.AttributeValue;
import com.padr.gys.infra.outbound.persistence.attribute.repository.AttributeValueRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class AttributeValuePersistencePortImpl implements AttributeValuePersistencePort {

    private final AttributeValueRepository attributeValueRepository;

    @Override
    public List<AttributeValue> saveAll(List<AttributeValue> attributeValues) {
        return attributeValueRepository.saveAll(attributeValues);
    }

    @Override
    public List<AttributeValue> findAllByIds(List<Long> ids) {
        return attributeValueRepository.findAllById(ids);
    }

    @Override
    public List<AttributeValue> findByAttributeId(Long attributeId) {
        return attributeValueRepository.findByAttributeId(attributeId);
    }
}
