package com.padr.gys.infra.outbound.persistence.categorization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.categorization.entity.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    
    List<SubCategory> findByCategoryId(Long categoryId);
}
