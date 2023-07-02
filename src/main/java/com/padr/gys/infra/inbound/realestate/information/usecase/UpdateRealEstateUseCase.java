package com.padr.gys.infra.inbound.realestate.information.usecase;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.address.port.AddressServicePort;
import com.padr.gys.domain.realestate.categorization.entity.Category;
import com.padr.gys.domain.realestate.categorization.entity.SubCategory;
import com.padr.gys.domain.realestate.categorization.port.CategoryServicePort;
import com.padr.gys.domain.realestate.categorization.port.SubCategoryServicePort;
import com.padr.gys.domain.realestate.information.entity.RealEstate;
import com.padr.gys.domain.realestate.information.exception.RealEstateAlreadyExistException;
import com.padr.gys.domain.realestate.information.port.RealEstateServicePort;
import com.padr.gys.infra.inbound.realestate.information.model.request.UpdateRealEstateRequest;
import com.padr.gys.infra.inbound.realestate.information.model.response.RealEstateResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateRealEstateUseCase {

    private final RealEstateServicePort realEstateServicePort;
    private final AddressServicePort addressServicePort;
    private final CategoryServicePort categoryServicePort;
    private final SubCategoryServicePort subCategoryServicePort;

    public RealEstateResponse execute(Long realEstateId, UpdateRealEstateRequest request) {
        Optional.ofNullable(realEstateServicePort.findByNoAndIsActive(request.getNo(), true)).ifPresent(r -> {
            throw new RealEstateAlreadyExistException(r.getNo());
        });

        RealEstate oldRealEstate = realEstateServicePort.findByIdAndIsActive(realEstateId, true);

        addressServicePort.update(oldRealEstate.getAddress().getId(), request.getAddressRequest().to());

        Category category = categoryServicePort.findByIdAndIsActive(request.getCategoryId(), true);
        SubCategory subCategory = Objects.nonNull(request.getSubCategoryId())
                ? subCategoryServicePort.findByIdAndIsActive(request.getSubCategoryId(), true)
                : null;

        RealEstate updatedRealEstate = request.to();
        updatedRealEstate.setCategory(category);
        updatedRealEstate.setSubCategory(subCategory);

        return RealEstateResponse.of(realEstateServicePort.update(realEstateId, updatedRealEstate));
    }
}
