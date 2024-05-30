package com.padr.gys.infra.inbound.rest.realestate.usecase;

import java.util.NoSuchElementException;
import java.util.Objects;

import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import jakarta.persistence.EntityExistsException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.address.entity.Address;
import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.infra.inbound.rest.realestate.model.request.CreateRealEstateRequest;
import com.padr.gys.infra.inbound.rest.realestate.model.response.RealEstateResponse;
import com.padr.gys.infra.outbound.persistence.address.port.AddressPersistencePort;
import com.padr.gys.infra.outbound.persistence.categorization.port.CategoryPersistencePort;
import com.padr.gys.infra.outbound.persistence.categorization.port.SubCategoryPersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateRealEstateUseCase {

    private final AddressPersistencePort addressPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;
    private final SubCategoryPersistencePort subCategoryPersistencePort;
    private final RealEstatePersistencePort realEstatePersistencePort;

    private final MessageSource messageSource;

    public RealEstateResponse execute(CreateRealEstateRequest request) {
        Address address = addressPersistencePort.save(request.getAddressRequest().to());

        Category category = categoryPersistencePort.findById(request.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException(messageSource
                        .getMessage("categorization.category.not-found", null, LocaleContextHolder.getLocale())));

        SubCategory subCategory = null;

        if (Objects.nonNull(request.getSubCategoryId())) {
            subCategory = subCategoryPersistencePort.findById(request.getSubCategoryId())
                    .orElseThrow(() -> new NoSuchElementException(messageSource
                            .getMessage("categorization.subcategory.not-found", null,
                                    LocaleContextHolder.getLocale())));
        }

        realEstatePersistencePort.findByNo(request.getNo()).ifPresent(r -> {
            throw new EntityExistsException(
                    messageSource.getMessage("realestate.already-exist", null, LocaleContextHolder.getLocale()));
        });

        RealEstate realEstate = request.to();
        realEstate.setCategory(category);
        realEstate.setSubCategory(subCategory);
        realEstate.setAddress(address);

        return RealEstateResponse.of(realEstatePersistencePort.save(realEstate));
    }
}
