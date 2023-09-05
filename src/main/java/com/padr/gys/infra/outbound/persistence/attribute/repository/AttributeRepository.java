package com.padr.gys.infra.outbound.persistence.attribute.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.attribute.entity.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    Page<Attribute> findByCategoryIdOrderByScreenOrderAsc(Long categoryId, Pageable pageable);

    Page<Attribute> findBySubCategoryIdOrderByScreenOrderAsc(Long subCategoryId, Pageable pageable);
}
