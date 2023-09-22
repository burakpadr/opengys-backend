package com.padr.gys.infra.outbound.persistence.categorization.port;

import java.util.List;
import java.util.Optional;

import com.padr.gys.domain.categorization.entity.persistence.SubCategory;

public interface SubCategoryPersistencePort {

    Optional<SubCategory> findById(Long id);
    
    List<SubCategory> findByCategoryId(Long categoryId);

    List<SubCategory> saveAll(List<SubCategory> subCategories);
}
