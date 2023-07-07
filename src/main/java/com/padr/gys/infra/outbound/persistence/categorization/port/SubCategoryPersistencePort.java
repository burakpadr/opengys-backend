package com.padr.gys.infra.outbound.persistence.categorization.port;

import java.util.List;
import java.util.Optional;

import com.padr.gys.domain.categorization.entity.SubCategory;

public interface SubCategoryPersistencePort {

    Optional<SubCategory> findByIdAndIsActive(Long id, Boolean isActive);
    
    List<SubCategory> findByCategoryIdAndIsActive(Long categoryId, Boolean isActive);

    List<SubCategory> saveAll(List<SubCategory> subCategories);
}
