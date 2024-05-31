package com.padr.gys.infra.inbound.rest.realestate.usecase;

import java.util.NoSuchElementException;
import java.util.Objects;

import com.padr.gys.domain.dashboard.context.DashboardHandlerContext;
import com.padr.gys.domain.dashboard.entity.RealEstateDistributionByCategoriesStatistic;
import com.padr.gys.infra.outbound.persistence.realestate.port.RealEstatePersistencePort;
import jakarta.persistence.EntityExistsException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.padr.gys.domain.address.entity.Address;
import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.infra.inbound.rest.realestate.model.request.UpdateRealEstateRequest;
import com.padr.gys.infra.inbound.rest.realestate.model.response.RealEstateDetailResponse;
import com.padr.gys.infra.outbound.persistence.address.port.AddressPersistencePort;
import com.padr.gys.infra.outbound.persistence.categorization.port.CategoryPersistencePort;
import com.padr.gys.infra.outbound.persistence.categorization.port.SubCategoryPersistencePort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateRealEstateUseCase {

    private final AddressPersistencePort addressPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;
    private final SubCategoryPersistencePort subCategoryPersistencePort;
    private final RealEstatePersistencePort realEstatePersistencePort;

    private final MessageSource messageSource;

    public RealEstateDetailResponse execute(Long realEstateId, UpdateRealEstateRequest request) {
        RealEstate oldRealEstate = realEstatePersistencePort.findById(realEstateId)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("realestate.not-found", null, LocaleContextHolder.getLocale())));

        updateAddress(oldRealEstate.getAddress().getId(), request.getAddressRequest().to());

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

        // Update real estate

        realEstatePersistencePort.findByNo(request.getNo()).ifPresent(r -> {
            if (!realEstateId.equals(r.getId()))
                throw new EntityExistsException(
                        messageSource.getMessage("realestate.already-exist", null, LocaleContextHolder.getLocale()));
        });

        oldRealEstate.setNo(request.getNo());
        oldRealEstate.setCategory(category);
        oldRealEstate.setSubCategory(subCategory);

        realEstatePersistencePort.save(oldRealEstate);

        // Update cache

        DashboardHandlerContext.getDashboardHandler(RealEstateDistributionByCategoriesStatistic.class)
                .updateAllStatisticElements();

        return RealEstateDetailResponse.of(oldRealEstate);
    }

    private void updateAddress(Long addressId, Address updateAddress) {
        Address oldAddress = addressPersistencePort.findById(addressId)
                .orElseThrow(() -> new NoSuchElementException(
                        messageSource.getMessage("address.not-found", null, LocaleContextHolder.getLocale())));

        oldAddress.setLatitude(updateAddress.getLatitude());
        oldAddress.setLongitude(updateAddress.getLongitude());
        oldAddress.setCityName(updateAddress.getCityName());
        oldAddress.setDistrictName(updateAddress.getDistrictName());
        oldAddress.setNeighborhoodName(updateAddress.getNeighborhoodName());
        oldAddress.setPostCode(updateAddress.getPostCode());
        oldAddress.setOpenAddress(updateAddress.getOpenAddress());

        addressPersistencePort.save(oldAddress);
    }
}
