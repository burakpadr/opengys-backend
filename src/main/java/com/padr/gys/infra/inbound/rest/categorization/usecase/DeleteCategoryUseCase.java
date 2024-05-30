package com.padr.gys.infra.inbound.rest.categorization.usecase;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.attribute.entity.Attribute;
import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.infra.outbound.persistence.attribute.port.AttributePersistencePort;
import com.padr.gys.infra.outbound.persistence.categorization.port.CategoryPersistencePort;
import com.padr.gys.infra.outbound.persistence.categorization.port.SubCategoryPersistencePort;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteCategoryUseCase {

    private final AttributePersistencePort attributePersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;
    private final SubCategoryPersistencePort subCategoryPersistencePort;
    private final RealEstatePersistencePort realEstatePersistencePort;

    private MessageSource messageSource;

    public void execute(Long categoryId) {
        Category category = categoryPersistencePort.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException(messageSource
                        .getMessage("categorization.category.not-found", null, LocaleContextHolder.getLocale())));

        deleteSubCategories(category.getSubCategories());
        deleteRealEstatesRelatedWithCategory(category.getRealEstates());
        deleteAttributes(category.getAttributes());
        deleteCategory(category);
    }

    public void deleteAttributes(List<Attribute> attributes) {
        attributes.stream().forEach(attribute -> {
            attribute.setIsDeleted(true);
        });

        attributePersistencePort.saveAll(attributes);
    }

    private void deleteRealEstatesRelatedWithCategory(List<RealEstate> realEstates) {
        realEstates.stream().forEach(realEstate -> {
            realEstate.setIsDeleted(true);
        });

        realEstatePersistencePort.saveAll(realEstates);
    }

    private void deleteSubCategories(List<SubCategory> subCategories) {
        subCategories.stream().forEach(subCategory -> {
            subCategory.setIsDeleted(true);
        });

        subCategoryPersistencePort.saveAll(subCategories);
    }

    private void deleteCategory(Category category) {
        category.setIsDeleted(true);

        categoryPersistencePort.save(category);
    }
}
