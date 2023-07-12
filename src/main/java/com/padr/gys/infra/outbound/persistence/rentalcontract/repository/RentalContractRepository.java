package com.padr.gys.infra.outbound.persistence.rentalcontract.repository;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalContractRepository extends JpaRepository<RentalContract, Long> {

    Page<RentalContract> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive, Pageable pageable);

    List<RentalContract> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive);

    Optional<RentalContract> findByIdAndIsActive(Long id, Boolean isActive);
}
