package com.padr.gys.domain.categorization.port;

import java.util.List;

import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;

public interface SubCategoryServicePort {
    
    List<SubCategory> findByCategoryId(Long categoryId);

    SubCategory findById(Long id);

    List<SubCategory> createAll(List<SubCategory> subCategories);

    void updateAll(Category category, List<SubCategory> subCategories);

    void deleteAll(List<SubCategory> subCategories);

}
