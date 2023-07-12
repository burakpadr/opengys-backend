package com.padr.gys.infra.outbound.persistence.rentalcontract.port;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RentalContractPersistencePort {

    Page<RentalContract> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive, Pageable pageable);

    List<RentalContract> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive);

    Optional<RentalContract> findByIdAndIsActive(Long id, Boolean isActive);

    RentalContract save(RentalContract rentalContract);
}
