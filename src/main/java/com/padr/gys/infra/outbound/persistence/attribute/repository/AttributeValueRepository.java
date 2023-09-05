package com.padr.gys.infra.outbound.persistence.attribute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.attribute.entity.AttributeValue;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {
    
    List<AttributeValue> findByAttributeId(Long attributeId);
}
