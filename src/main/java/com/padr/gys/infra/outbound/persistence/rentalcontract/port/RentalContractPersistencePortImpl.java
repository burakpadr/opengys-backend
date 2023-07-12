package com.padr.gys.infra.outbound.persistence.rentalcontract.port;

import com.padr.gys.domain.rentalcontract.entity.RentalContract;
import com.padr.gys.infra.outbound.persistence.rentalcontract.repository.RentalContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RentalContractPersistencePortImpl implements  RentalContractPersistencePort {

    private final RentalContractRepository rentalContractRepository;

    @Override
    public Page<RentalContract> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive, Pageable pageable) {
        return rentalContractRepository.findByRealEstateIdAndIsActive(realEstateId, isActive, pageable);
    }

    @Override
    public List<RentalContract> findByRealEstateIdAndIsActive(Long realEstateId, Boolean isActive) {
        return rentalContractRepository.findByRealEstateIdAndIsActive(realEstateId, isActive);
    }

    @Override
    public Optional<RentalContract> findByIdAndIsActive(Long id, Boolean isActive) {
        return rentalContractRepository.findByIdAndIsActive(id, isActive);
    }

    @Override
    public RentalContract save(RentalContract rentalContract) {
        return rentalContractRepository.save(rentalContract);
    }
}
