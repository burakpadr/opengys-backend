package com.padr.gys.infra.outbound.persistence.rentalcontract.repository;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalContractRepository extends JpaRepository<RentalContract, Long> {

    @Query("SELECT rc FROM RentalContract rc WHERE rc.realEstate.id = :realEstateId ORDER BY rc.id DESC")
    Page<RentalContract> findByRealEstateId(Long realEstateId, Pageable pageable);

    @Query("SELECT rc FROM RentalContract rc WHERE rc.realEstate.id = :realEstateId ORDER BY rc.id DESC")
    List<RentalContract> findByRealEstateId(Long realEstateId);

    List<RentalContract> findByRealEstateIdAndIsPublished(Long realEstateId, Boolean isPublished);

    List<RentalContract> findByTenantId(Long tenantId);
}
