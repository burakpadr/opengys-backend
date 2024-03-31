package com.padr.gys.infra.outbound.persistence.rentalcontract.port;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RentalContractPersistencePort {

    Page<RentalContract> findByRealEstateId(Long realEstateId, Pageable pageable);

    List<RentalContract> findByRealEstateId(Long realEstateId);

    List<RentalContract> findByRealEstateIdAndIsPublished(Long realEstateId, Boolean isPublished);

    List<RentalContract> findByTenantId(Long tenantId);

    Optional<RentalContract> findById(Long id);

    RentalContract save(RentalContract rentalContract);

    List<RentalContract> saveAll(List<RentalContract> rentalContracts);
}
