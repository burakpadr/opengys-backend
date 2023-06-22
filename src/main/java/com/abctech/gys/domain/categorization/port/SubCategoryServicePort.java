package com.abctech.gys.domain.categorization.port;

import java.util.List;

import com.abctech.gys.domain.categorization.entity.SubCategory;

public interface SubCategoryServicePort {
    
    List<SubCategory> findByCategoryIdAndIsActive(Long categoryId, Boolean isActive);

    List<SubCategory> createAll(List<SubCategory> subCategories);

    List<SubCategory> updateAll(Long categoryId, List<SubCategory> subCategories);

    void deleteAll(List<Long> subCategoryIds);

}
