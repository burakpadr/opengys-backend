package com.padr.gys.infra.inbound.realestate.usecase;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.address.port.AddressServicePort;
import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;
import com.padr.gys.domain.categorization.port.CategoryServicePort;
import com.padr.gys.domain.categorization.port.SubCategoryServicePort;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;
import com.padr.gys.infra.inbound.realestate.model.request.UpdateRealEstateRequest;
import com.padr.gys.infra.inbound.realestate.model.response.RealEstateDetailResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateRealEstateUseCase {

    private final RealEstateServicePort realEstateServicePort;
    private final AddressServicePort addressServicePort;
    private final CategoryServicePort categoryServicePort;
    private final SubCategoryServicePort subCategoryServicePort;

    public RealEstateDetailResponse execute(Long realEstateId, UpdateRealEstateRequest request) {
        RealEstate oldRealEstate = realEstateServicePort.findByIdAndIsActive(realEstateId, true);

        addressServicePort.update(oldRealEstate.getAddress().getId(), request.getAddressRequest().to());

        Category category = categoryServicePort.findByIdAndIsActive(request.getCategoryId(), true);
        SubCategory subCategory = Objects.nonNull(request.getSubCategoryId())
                ? subCategoryServicePort.findById(request.getSubCategoryId())
                : null;

        RealEstate updatedRealEstate = request.to();
        updatedRealEstate.setCategory(category);
        updatedRealEstate.setSubCategory(subCategory);

        return RealEstateDetailResponse.of(realEstateServicePort.update(realEstateId, updatedRealEstate));
    }
}
