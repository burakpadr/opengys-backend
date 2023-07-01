package com.padr.gys.infra.outbound.persistence.categorization.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padr.gys.domain.realestate.categorization.entity.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    
    List<SubCategory> findByCategoryIdAndIsActive(Long categoryId, Boolean isActive);

    Optional<SubCategory> findByIdAndIsActive(Long id, Boolean isActive);
}
