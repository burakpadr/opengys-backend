package com.padr.gys.domain.realestate.categorization.port;

import java.util.List;

import com.padr.gys.domain.realestate.categorization.entity.Category;
import com.padr.gys.domain.realestate.categorization.entity.SubCategory;

public interface SubCategoryServicePort {
    
    List<SubCategory> findByCategoryIdAndIsActive(Long categoryId, Boolean isActive);

    List<SubCategory> createAll(List<SubCategory> subCategories);

    void updateAll(Category category, List<SubCategory> subCategories);

    void deleteAll(List<Long> subCategoryIds);

}