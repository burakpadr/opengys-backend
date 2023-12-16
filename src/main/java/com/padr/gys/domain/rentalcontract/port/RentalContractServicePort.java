package com.padr.gys.domain.rentalcontract.port;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RentalContractServicePort {

    Page<RentalContract> findByRealEstateId(Long realEstateId, Pageable pageable);

    List<RentalContract> findByRealEstateIdAndIsPublished(Long realEstateId, Boolean isPublished);

    RentalContract findById(Long id);

    RentalContract create(RentalContract rentalContract);

    RentalContract update(Long id, RentalContract rentalContract);

    void delete(Long id);
}
