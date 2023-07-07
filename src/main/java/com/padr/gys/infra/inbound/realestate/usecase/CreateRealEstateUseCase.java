package com.padr.gys.infra.inbound.realestate.usecase;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.address.entity.Address;
import com.padr.gys.domain.address.port.AddressServicePort;
import com.padr.gys.domain.categorization.entity.Category;
import com.padr.gys.domain.categorization.entity.SubCategory;
import com.padr.gys.domain.categorization.port.CategoryServicePort;
import com.padr.gys.domain.categorization.port.SubCategoryServicePort;
import com.padr.gys.domain.realestate.entity.RealEstate;
import com.padr.gys.domain.realestate.port.RealEstateServicePort;
import com.padr.gys.infra.inbound.realestate.model.request.CreateRealEstateRequest;
import com.padr.gys.infra.inbound.realestate.model.response.RealEstateResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateRealEstateUseCase {

    private final RealEstateServicePort realEstateServicePort;
    private final AddressServicePort addressServicePort;
    private final CategoryServicePort categoryServicePort;
    private final SubCategoryServicePort subCategoryServicePort;

    public RealEstateResponse execute(CreateRealEstateRequest request) {
        Address address = addressServicePort.create(request.getAddressRequest().to());

        Category category = categoryServicePort.findByIdAndIsActive(request.getCategoryId(), true);
        SubCategory subCategory = Objects.nonNull(request.getSubCategoryId())
                ? subCategoryServicePort.findByIdAndIsActive(request.getSubCategoryId(), true)
                : null;

        RealEstate realEstate = request.to();
        realEstate.setCategory(category);
        realEstate.setSubCategory(subCategory);
        realEstate.setAddress(address);

        return RealEstateResponse.of(realEstateServicePort.create(realEstate));
    }
}
