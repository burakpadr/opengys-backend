package com.padr.gys.domain.categorization.port;

import java.util.List;

import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;

public interface SubCategoryServicePort {
    
    List<SubCategory> findByCategoryIdAndIsActive(Long categoryId, Boolean isActive);

    SubCategory findByIdAndIsActive(Long id, Boolean isActive);

    List<SubCategory> createAll(List<SubCategory> subCategories);

    void updateAll(Category category, List<SubCategory> subCategories);

    void deleteAll(List<Long> subCategoryIds);

}
