package com.padr.gys.domain.rentalcontract.port;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RentalContractServicePort {

    Page<RentalContract> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive, Pageable pageable);

    RentalContract findByIdAndIsActive(Long id, Boolean isActive);

    RentalContract create(RentalContract rentalContract);

    RentalContract update(Long id, RentalContract rentalContract);

    void delete(Long id);
}
