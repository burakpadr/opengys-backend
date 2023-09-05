package com.padr.gys.infra.outbound.persistence.attribute.port;

import java.util.List;

import com.padr.gys.domain.attribute.entity.AttributeValue;

public interface AttributeValuePersistencePort {
    
    List<AttributeValue> findAllByIds(List<Long> ids);

    List<AttributeValue> saveAll(List<AttributeValue> attributeValues);

    List<AttributeValue> findByAttributeId(Long attributeId);
}
