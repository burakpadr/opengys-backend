package com.padr.gys.domain.attribute.port;

import java.util.List;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.domain.attribute.entity.AttributeValue;

public interface AttributeValueServicePort {

    List<AttributeValue> createAll(List<AttributeValue> attributeValues);

    void updateAll(Attribute attribute, List<AttributeValue> attributeValues);

    void deleteAll(List<Long> attributeValueIds);
}
