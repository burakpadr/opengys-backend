package com.padr.gys.infra.inbound.realestate.information.usecase;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.padr.gys.domain.realestate.address.entity.Address;
import com.padr.gys.domain.realestate.address.port.AddressServicePort;
import com.padr.gys.domain.realestate.categorization.entity.Category;
import com.padr.gys.domain.realestate.categorization.entity.SubCategory;
import com.padr.gys.domain.realestate.categorization.port.CategoryServicePort;
import com.padr.gys.domain.realestate.categorization.port.SubCategoryServicePort;
import com.padr.gys.domain.realestate.information.entity.RealEstate;
import com.padr.gys.domain.realestate.information.exception.RealEstateAlreadyExistException;
import com.padr.gys.domain.realestate.information.port.RealEstateServicePort;
import com.padr.gys.infra.inbound.realestate.information.model.request.CreateRealEstateRequest;
import com.padr.gys.infra.inbound.realestate.information.model.response.RealEstateResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateRealEstateUseCase {

    private final RealEstateServicePort realEstateServicePort;
    private final AddressServicePort addressServicePort;
    private final CategoryServicePort categoryServicePort;
    private final SubCategoryServicePort subCategoryServicePort;

    public RealEstateResponse execute(CreateRealEstateRequest request) {
        Optional.ofNullable(realEstateServicePort.findByNoAndIsActive(request.getNo(), true)).ifPresent(r -> {
            throw new RealEstateAlreadyExistException(r.getNo());
        });

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
