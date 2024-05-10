package com.padr.gys.domain.rentalcontract.port;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RentalContractServicePort {

    Page<RentalContract> findByRealEstateId(Long realEstateId, Pageable pageable);

    List<RentalContract> findByRealEstateIdAndIsPublished(Long realEstateId, Boolean isPublished);

    List<RentalContract> findByTenantId(Long tenantId);

    RentalContract findById(Long id);

    RentalContract save(RentalContract rentalContract);

    RentalContract update(RentalContract oldRentalContract, RentalContract newRentalContract);

    void delete(Long id);

    void deleteAll(List<RentalContract> rentalContracts);
}
